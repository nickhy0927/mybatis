package com.iss;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

	@RequestMapping(value = "/index.do")
	public String index(Model model) {
		model.addAttribute("username", "zhangsan");
		return "index";
	}
}
