package com.iss.index;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mybatis.common.utils.MessageObject;
import com.mybatis.interceptor.MessageResources;
import com.mybatis.platform.menu.entity.MenuTemplate;
import com.mybatis.platform.menu.service.MenuService;

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
//        throw new RuntimeException("自定义异常");
        return view;
    }

    @ResponseBody
    @RequestMapping(value = "/change.json", method = RequestMethod.POST)
    public MessageObject change(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
        messageObject.ok("语言切换成功", null);
        return messageObject;
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public ModelAndView logout(HttpSession session) {
        session.invalidate();
        return new ModelAndView("redirect:index.do");
    }
}
