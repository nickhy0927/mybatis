package com.mybatis.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/test")
public class TestController {

    @RequestMapping(value = "toTestFtlPage.do", method = RequestMethod.GET)
    public ModelAndView toTestFtlPage(HttpServletResponse response, HttpServletRequest request) {
        ModelAndView view = new ModelAndView("pages/test");
        view.addObject("username", "yuanhuangd");
        return view;
    }

    @RequestMapping(value = "copy/test1.do", method = RequestMethod.GET)
    public ModelAndView testCopy(HttpServletResponse response, HttpServletRequest request) {
        ModelAndView view = new ModelAndView("module/test/test");
        view.addObject("username", "yuanhuangd");
        return view;
    }

    @RequestMapping(value = "/parent/base.do", method = RequestMethod.GET)
    public ModelAndView getBasePage() {
        ModelAndView view = new ModelAndView("parent/base");
        view.addObject("username", "yuanhuangd");
        return view;
    }
}
