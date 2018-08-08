package com.iss.index;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/")
public class IndexController {

    @RequestMapping(value = "index.do", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("index");
        return view;
    }

    @RequestMapping(value = "main.do", method = RequestMethod.GET)
    public ModelAndView main() {
        ModelAndView view = new ModelAndView("main");
        return view;
    }

    public static void main(String[] args) throws IOException {

    }
}
