package com.iss.module.platform.user.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

    @RequestMapping(value = "/platform/user/userList.do", method = RequestMethod.GET)
    public String userList(Model model) {
        return "platform/user/userList";
    }
}
