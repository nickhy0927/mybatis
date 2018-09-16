package com.authority.auth.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.mybatis.utils.MessageObject;

public class AjaxAuthenticationFailureHandler implements AuthenticationFailureHandler {
	protected final Log logger = LogFactory.getLog(getClass());

	public AjaxAuthenticationFailureHandler() {
	}

	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		response.setHeader("Content-Type", "application/json;charset=UTF-8");
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication failed");
		MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
		messageObject.setErrorMessage("登录失败，用户名或密码错误");
		messageObject.returnData(response, messageObject);
	}

}