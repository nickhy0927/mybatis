package com.mybatis.singleton;


import com.mybatis.common.utils.JsonMapper;
import com.mybatis.platform.user.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserSingleton {

    public static final String SITE_USER = "SITE_USER";

    private static ThreadLocal<HttpServletRequest> requestThreadLocal = new ThreadLocal<>();

    private static ThreadLocal<HttpServletResponse> responseThreadLocal = new ThreadLocal<>();

    /**
     * 设置 HttpServletRequest，HttpServletResponse
     * @param request
     * @param response
     */
    public static void set(HttpServletRequest request, HttpServletResponse response) {
        requestThreadLocal.set(request);
        responseThreadLocal.set(response);
    }

    /**
     * 退出
     * @param request
     */
    public static void removeUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute(SITE_USER);
    }

    public static void setUser(HttpServletRequest request, User user) {
        HttpSession session = request.getSession();
        session.setAttribute(SITE_USER, new JsonMapper().toJson(user));
    }

    public static User getContextUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object o = session.getAttribute(SITE_USER);
        if (o != null)
            return new JsonMapper().fromJson(o.toString(), User.class);
        else
            return null;

    }

    /**
     * 获取 HttpServletResponse
     * @return
     */
    public static HttpServletResponse getHttpServletResponse() {
        return responseThreadLocal.get();
    }

    /**
     * 获取 HttpServletRequest
     * @return
     */
    public static HttpServletRequest getHttpServletRequest() {
        return requestThreadLocal.get();
    }


}
