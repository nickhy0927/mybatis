package com.iss.module.index;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.iss.middleware.redis.propertis.RedisProperties;
import com.iss.module.platform.user.pojo.User;
import com.iss.module.platform.user.service.UserService;

@Controller
public class IndexController {

	@Autowired
	private UserService userService;

	@Autowired
	private RedisProperties redisProperties;

	@RequestMapping(value = "/index.do")
	public String index(Model model) {
		List<User> users = userService.queryPageByMap();
		model.addAttribute("users", users);
		String password = redisProperties != null ? redisProperties.getPassword() : "密码为空";
		System.out.println(password);
		return "index";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {

		return "login";
	}
}
