package com.iss.index;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.mybatis.utils.MessageObject;

@Controller
public class SecurityController {

	private static Logger logger = LoggerFactory.getLogger(SecurityController.class);

	private RequestCache requestCache = new HttpSessionRequestCache();

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@RequestMapping(value = "/authentication/require.do")
	@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
	public MessageObject requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
		if (savedRequest != null) {
			String redirectUrl = savedRequest.getRedirectUrl();
			logger.debug("引发跳转的请求是：", redirectUrl);
			if (StringUtils.endsWithIgnoreCase(redirectUrl, ".do")) {
				String url = "";
				redirectStrategy.sendRedirect(request, response, url);
			} else {
				messageObject.error("你无权限访问, 该请求需要身份验证");
			}
		}
		return messageObject;
	}
}
