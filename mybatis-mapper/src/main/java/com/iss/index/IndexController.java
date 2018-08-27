package com.iss.index;

import com.mybatis.core.orm.config.SpringContextHolder;
import com.mybatis.interceptor.MessageResources;
import com.mybatis.platform.menu.entity.MenuTemplate;
import com.mybatis.platform.menu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@Controller
public class IndexController {

    @Autowired
    MessageResources messageResources;

    public String getTextValue(String key) {
        return messageResources.getMessage(key, null);
    }

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
        view.addObject("test", getTextValue("test.msg"));
        System.out.println(getTextValue("test.msg"));
        return view;
    }

    public static void main(String[] args) throws IOException {

    }


}
