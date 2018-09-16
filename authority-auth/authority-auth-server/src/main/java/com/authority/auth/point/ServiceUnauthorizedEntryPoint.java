package com.authority.auth.point;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

public class ServiceUnauthorizedEntryPoint extends LoginUrlAuthenticationEntryPoint {

	public ServiceUnauthorizedEntryPoint(String loginFormUrl) {
		super(loginFormUrl);
	}

	private static final Logger logger = LoggerFactory.getLogger(AuthenticationEntryPoint.class);

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		if (isAjaxRequest(request)) {
			logger.debug("是ajax登录");
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
		} else {
			response.sendRedirect("/login.do");
		}
	}

	public static boolean isAjaxRequest(HttpServletRequest request) {
		String ajaxFlag = request.getHeader("X-Requested-With");
		return ajaxFlag != null && "XMLHttpRequest".equals(ajaxFlag);
	}
}
