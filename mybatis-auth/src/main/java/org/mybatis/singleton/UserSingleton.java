package org.mybatis.singleton;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserSingleton {

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
