package com.mybatis.interceptor;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.google.common.collect.Sets;
import com.mybatis.common.utils.InitEnvironment;
import com.mybatis.common.utils.MessageObject;
import com.mybatis.common.utils.SpringContextHolder;
import com.mybatis.platform.user.entity.User;
import com.mybatis.singleton.UserSingleton;

/**
 * 权限拦截器
 *
 * @author Hyuan
 */
public class PremissionDispatcherFilter extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        UserSingleton.set(request, response);
        User user = UserSingleton.getContextUser(request);
        if (user == null) {
            return true;
        }
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            String[] menus = {};
            System.out.println(menus.length);
            Set<String> aliasList = Sets.newHashSet();
            for (String alias : menus) {
                aliasList.add(alias);
            }
            Authority access = method.getAnnotation(Authority.class);
            String uri = request.getRequestURI();
            StaticResources staticResources = StaticResources.getStaticResourcesInstance();
            List<String> urls = staticResources.getUrls();
            for (String url : urls) {
                if (uri.contains(url)) {
                    return true;
                }
            }
            InitEnvironment environment = SpringContextHolder.getBean(InitEnvironment.class);
            if (environment.getInitUsername().equals(user.getLoginName())) {
                return true;
            }
            if (uri.lastIndexOf(".json") > 0) {
                if (access != null) {
                    String alias = access.alias();
                    if (aliasList.contains(alias)) {
                        return true;
                    } else {
                        MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
                        messageObject.error("你没有操作权限访问，如需操作，请联系管理员");
                        messageObject.returnData(response, messageObject);
                        return false;
                    }
                }
            } else {
                if (access != null) {
                    String alias = access.alias();
                    if (aliasList.contains(alias)) {
                        return true;
                    } else {
                        request.setAttribute("url", uri);
                        request.getRequestDispatcher("/unauth.do").forward(request, response);
                        return false;
                    }
                }
            }
        }
        return true;
    }

}
