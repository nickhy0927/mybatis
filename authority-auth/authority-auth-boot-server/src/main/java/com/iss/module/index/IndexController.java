package com.iss.module.index;

import com.iss.middleware.redis.config.JedisUtil;
import com.iss.module.platform.user.pojo.User;
import com.iss.module.platform.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collections;
import java.util.List;

@Controller
public class IndexController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/index.do")
	public String index(Model model) {
		List<User> users = userService.queryPageByMap();
		model.addAttribute("users", users);
		JedisUtil.setObject("userList", users, Integer.MAX_VALUE);
		return "index";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {

		return "login";
	}
}
