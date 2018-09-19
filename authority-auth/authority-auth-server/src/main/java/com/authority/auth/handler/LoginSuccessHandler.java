package com.authority.auth.handler;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.authority.auth.interceptor.StaticConfig;
import com.authority.security.SecurityUser;
import com.mybatis.common.utils.MD5;

/**
 * 登录成功处理器
 */
public class LoginSuccessHandler implements AuthenticationSuccessHandler, InitializingBean {

	private static Logger logger = LoggerFactory.getLogger(LoginSuccessHandler.class);

	private String defaultTargetUrl;

	private boolean forwardToDestination = false;

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
		System.out.println("登录用户：username=" + securityUser.getUsername() + ", uri=" + request.getContextPath());
		String token = UUID.randomUUID().toString().replaceAll("-", "");
		if (this.forwardToDestination) {
			logger.info("Login success,Forwarding to " + this.defaultTargetUrl);
			request.getRequestDispatcher(this.defaultTargetUrl).forward(request, response);
		} else {
			String backUrl = StaticConfig.backUrl + "?jsessionid=" + MD5.MD5Encode(token.toLowerCase());
			logger.info("Login success,Redirecting to " + this.defaultTargetUrl);
			request.getSession().setAttribute("token", token);
			boolean empty = StringUtils.isNoneEmpty(StaticConfig.backUrl);
			response.setHeader("token", token.toLowerCase());
			StringBuffer buffer = new StringBuffer();
			if (backUrl.contains("?"))
				buffer.append("&");
			buffer.append("token").append("=").append(token);
			this.redirectStrategy.sendRedirect(request, response, empty ? backUrl + buffer.toString() : this.defaultTargetUrl);
		}

	}

	public String getIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public void setDefaultTargetUrl(String defaultTargetUrl) {
		this.defaultTargetUrl = defaultTargetUrl;
	}

	public void setForwardToDestination(boolean forwardToDestination) {
		this.forwardToDestination = forwardToDestination;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if (StringUtils.isEmpty(defaultTargetUrl))
			throw new Exception("You must configure defaultTargetUrl");
	}

}