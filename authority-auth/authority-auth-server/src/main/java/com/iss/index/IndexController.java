package com.iss.index;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping(value = "/index.do")
	public String index(Model model) {
		model.addAttribute("msg", "登录成功");
		return "index";
	}
}
