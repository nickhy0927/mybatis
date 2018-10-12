package com.mybatis.interceptor;

import com.mybatis.common.utils.MessageObject;
import com.mybatis.platform.user.entity.User;
import com.mybatis.singleton.UserSingleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;

/**
 * 拦截所有的请求
 *
 * @author yuanhuangd
 */
public class DispatcherFilter extends HandlerInterceptorAdapter {

    private final Logger LOG = LoggerFactory.getLogger(DispatcherFilter.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
        try {
            UserSingleton.set(request, response);
            String uri = request.getRequestURI();
            User user = UserSingleton.getContextUser(request);
            if (user != null && user.getId() == null) {
                user = null;
            }
            StaticResources staticResources = StaticResources.getStaticResourcesInstance();
            List<String> urls = staticResources.getUrls();
            AntPathMatcher pathMatcher = new AntPathMatcher();
            uri = uri.replace(request.getContextPath(), "");
            for (String url : urls) {
                boolean match = pathMatcher.match(url, uri);
                if (match) {
                    return true;
                }
            }
            String jsessionid = UUID.randomUUID().toString().replaceAll("-", "");
            String context = request.getContextPath();
            if (user == null) {
                if (uri.contains(context + staticResources.getLoginUrl())) {
                    return true;
                }
                if (uri.lastIndexOf(".json") > 0) {
                    messageObject.error("登录超时，请重新登录");
                    messageObject.returnData(response, messageObject);
                    return false;
                }
                response.sendRedirect(context + staticResources.getLoginUrl() + "?jsessionid=" + jsessionid);
                return false;
            } else {
                if (uri.endsWith(staticResources.getLoginUrl())) {
                    response.sendRedirect(context + staticResources.getSuccessUrl() + "?jsessionid=" + jsessionid);
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) {
        LOG.debug("你正在请求的地址是：" + request.getRequestURI());
        if (modelAndView != null) { // 加入当前时间
            modelAndView.addObject("jsessionid", UUID.randomUUID().toString().replace("-", "").toUpperCase());
        }
    }

}
