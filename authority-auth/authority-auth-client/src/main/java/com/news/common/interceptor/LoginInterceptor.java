package com.news.common.interceptor;

import com.news.SystemConfig;
import com.news.platform.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

public class LoginInterceptor extends HandlerInterceptorAdapter {

    private final static String TOKEN = "_TOKEN";

    @Autowired
    private SystemConfig systemConfig;

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //验证用户是否登陆
        Object object = request.getSession().getAttribute(TOKEN);
        if (object == null) {
            Enumeration<String> headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                System.out.println(headerNames.nextElement());
            }
            String token = response.getHeader("token");
            if (StringUtils.isEmpty(token)) {
                String url = "http://" + request.getServerName() //服务器地址
                        + ":"
                        + request.getServerPort()           //端口号
                        + request.getRequestURI();
                response.sendRedirect(systemConfig.getLoginPage() + "?backUrl=" + url);
                return false;
            } else  request.getSession().setAttribute(TOKEN, token);
        }

        return true;
    }
}
