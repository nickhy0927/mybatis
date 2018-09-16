package com.authority.auth.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.mybatis.utils.MessageObject;

public class AjaxAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	public AjaxAuthenticationSuccessHandler() {
	}

	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		response.setStatus(HttpServletResponse.SC_OK);
		response.setHeader("Content-Type", "application/json;charset=UTF-8");
		MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
		messageObject.setErrorMessage("登录失败，用户名或密码错误");
		messageObject.returnData(response, messageObject);
		

	}
}