package com.authority.auth.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class MyUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	 // 是否开启验证码功能
    private boolean isOpenValidateCode = true;

    public static final String VALIDATE_CODE = "validateCode";
    
    public MyUsernamePasswordAuthenticationFilter() {
        super();
        SimpleUrlAuthenticationFailureHandler failedHandler = (SimpleUrlAuthenticationFailureHandler)getFailureHandler();
        failedHandler.setDefaultFailureUrl("/user/login.do?validerror");
    }
    
    @Override  
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {  
        HttpServletRequest req = (HttpServletRequest) request;  
        HttpServletResponse res=(HttpServletResponse)response;
        
        if (!requiresAuthentication(req, res)) {
            chain.doFilter(request, response);
            return;
        }
        if (isOpenValidateCode) {
            if(!checkValidateCode(req, res))return;
        }
        //保存一些session信息
        HttpSession session = req.getSession();
        session.setAttribute(VALIDATE_CODE, "mytest");
        chain.doFilter(request,response);  
    }  
    
    /**
     * 覆盖授权验证方法，这里可以做一些自己需要的session设置操作
     */
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    protected boolean checkValidateCode(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();

        String sessionValidateCode = obtainSessionValidateCode(session);
        sessionValidateCode = "1234";// 做个假的验证码；
        // 让上一次的验证码失效
        session.setAttribute(VALIDATE_CODE, null);
        String validateCodeParameter = obtainValidateCodeParameter(request);
        if (StringUtils.isEmpty(validateCodeParameter) || !sessionValidateCode.equalsIgnoreCase(validateCodeParameter)) {
            unsuccessfulAuthentication(request, response, new InsufficientAuthenticationException("输入的验证码不正确"));  
            return false;
        }
        return true;
    }

    private String obtainValidateCodeParameter(HttpServletRequest request) {
        Object obj = request.getParameter(VALIDATE_CODE);
        return null == obj ? "" : obj.toString();
    }

    protected String obtainSessionValidateCode(HttpSession session) {
        Object obj = session.getAttribute(VALIDATE_CODE);
        return null == obj ? "" : obj.toString();
    }
}
