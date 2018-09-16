package com.authority.auth.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.authority.security.UserInfoEntity;

/**
 * 登录成功处理器
 */
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        UserInfoEntity userDetails = (UserInfoEntity) authentication.getPrincipal();
        System.out.println("登录用户：username=" + userDetails.getUsername() + ", uri=" + request.getContextPath());
        super.onAuthenticationSuccess(request, response, authentication);
    }
}