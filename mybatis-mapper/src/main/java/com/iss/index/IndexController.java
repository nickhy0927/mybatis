package com.iss.index;

import com.mybatis.interceptor.MessageResources;
import com.mybatis.platform.menu.entity.MenuTemplate;
import com.mybatis.platform.menu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private MenuService menuService;

    @RequestMapping(value = "/index.do", method = RequestMethod.GET)
    public ModelAndView index() {
        List<MenuTemplate> templates = menuService.queryIndexMenu();
        ModelAndView view = new ModelAndView("index");
        view.addObject("menuList", templates);
        return view;
    }

    @RequestMapping(value = "/main.do", method = RequestMethod.GET)
    public ModelAndView main() {
        ModelAndView view = new ModelAndView("main");
        String message = MessageResources.getMessage("test.msg");
        view.addObject("test", message);
        return view;
    }
}
