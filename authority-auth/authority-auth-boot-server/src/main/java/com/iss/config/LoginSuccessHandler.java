package com.iss.config;

import com.iss.module.platform.user.pojo.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录成功处理器
 */
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        User userDetails = (User) authentication.getPrincipal();
        System.out.println("登录用户：username=" + userDetails.getUsername() + ", uri=" + request.getContextPath());
        super.onAuthenticationSuccess(request, response, authentication);
    }
}