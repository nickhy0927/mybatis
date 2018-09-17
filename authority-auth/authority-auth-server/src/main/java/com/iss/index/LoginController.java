package com.iss.index;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.authority.auth.interceptor.StaticConfig;

@Controller
public class LoginController {

	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public String login(HttpServletRequest request) {
		String backUrl = request.getParameter("backUrl");
		System.out.println(backUrl);
		StaticConfig.backUrl = backUrl;
		return "login";
	}
}
