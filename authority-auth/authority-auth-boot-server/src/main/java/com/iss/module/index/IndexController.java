package com.iss.module.index;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iss.module.platform.user.pojo.User;
import com.iss.module.platform.user.service.UserService;

@Controller
public class IndexController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/index.do")
	public String index(Model model) {
		List<User> users = userService.queryPageByMap();
		model.addAttribute("users", users);
		return "index";
	}
	@RequestMapping(value = "/login.do")
	public String login(Model model) {
		List<User> users = userService.queryPageByMap();
		model.addAttribute("users", users);
		return "login";
	}
}
