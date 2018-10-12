package com.mybatis.error;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/")
public class ErrorController {

    @RequestMapping(value = "error404.do", method = RequestMethod.GET)
    public ModelAndView error404() {
        ModelAndView view = new ModelAndView("error404");
        return view;
    }
}
