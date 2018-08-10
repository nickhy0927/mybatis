package com.iss.index;

import com.google.common.collect.Maps;
import com.mybatis.common.utils.JsonMapper;
import com.mybatis.common.utils.RequestData;
import com.mybatis.platform.menu.entity.Menu;
import com.mybatis.platform.menu.service.MenuService;
import org.mybatis.singleton.UserSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/")
public class IndexController {

    @Autowired
    private MenuService menuService;

    @RequestMapping(value = "index.do", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("index");
        HttpServletRequest request = UserSingleton.getHttpServletRequest();
        try {
            Map<String, Object> dataToMap = RequestData.getRequestDataToMap(request);
            List<Menu> menuList = menuService.queryListByMap(dataToMap);
            view.addObject("menuList", menuList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return view;
    }

    @RequestMapping(value = "main.do", method = RequestMethod.GET)
    public ModelAndView main() {
        ModelAndView view = new ModelAndView("main");
        Map<String, Object> paramsMap = Maps.newConcurrentMap();
        List<Menu> menuList = menuService.queryListByMap(paramsMap);
        view.addObject("menuList", menuList);
        return view;
    }
}