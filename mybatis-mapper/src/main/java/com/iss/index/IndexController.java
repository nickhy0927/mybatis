package com.iss.index;

import com.mybatis.interceptor.MessageResources;
import com.mybatis.platform.menu.entity.MenuTemplate;
import com.mybatis.platform.menu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

@Controller
public class IndexController {

    @Autowired
    ResourceBundleMessageSource messageSource;

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
    public ModelAndView main(Locale locale) {
//        String s = MessageResources.getMessage("test.msg");
        String message = messageSource.getMessage("test.msg", null, locale);
        ModelAndView view = new ModelAndView("main");
        view.addObject("test", message);
        return view;
    }

    public static void main(String[] args) throws IOException {

    }


}
