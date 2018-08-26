package com.mybatis.platform.menu.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.mybatis.common.utils.JsonMapper;
import com.mybatis.common.utils.MessageObject;
import com.mybatis.common.utils.PageSupport;
import com.mybatis.common.utils.PagerInfo;
import com.mybatis.common.utils.RequestData;
import com.mybatis.core.orm.constant.SysConstant;
import com.mybatis.core.orm.core.exception.ServiceException;
import com.mybatis.core.orm.entity.PageRowBounds;
import com.mybatis.interceptor.Authority;
import com.mybatis.interceptor.OperateLog;
import com.mybatis.interceptor.OperateType;
import com.mybatis.platform.menu.entity.Menu;
import com.mybatis.platform.menu.entity.MenuTree;
import com.mybatis.platform.menu.service.MenuService;
import com.mybatis.utils.NumberCreate;

/**
 * @author yuanhuangd
 * @version V1.0
 * @Title: menuController.java
 * @Package com.mybatis.platform.menu.controller
 * @Description 菜单管理
 * @Date: 2018年6月9日 下午6:03:46
 */
@Controller
public class MenuController {

    @Autowired
    private MenuService menuService;

    /**
     * 新增页面
     */
    @RequestMapping(value = "/platform/menu/menu-create.do", method = RequestMethod.GET)
    @Authority(alias = "menu-create")
    public String menuCreate(Model model, HttpServletRequest request) {
        try {
            model.addAttribute("code", NumberCreate.generateNumber2());
            Map<String, Object> paramsMap = RequestData.getRequestDataToMap(request);
            paramsMap.put("status", SysConstant.DataStatus.VALID);
            List<Menu> menuList = menuService.queryListByMap(paramsMap);
            List<MenuTree> menuTrees = Lists.newArrayList();
            for (Menu menu : menuList) {
                menuTrees.add(new MenuTree(menu));
            }
            model.addAttribute("menuList", JsonMapper.toJson(menuTrees));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "module/platform/menu/menu-create";
    }

    /**
     * 新增数据到数据库
     */
    @ResponseBody
    @RequestMapping(value = "/platform/menu/menu-save.json", method = RequestMethod.POST, produces = "application/json")
    @OperateLog(message = "新增菜单信息", optType = OperateType.OptType.INSERT, service = MenuService.class)
    public MessageObject menuSave(Menu menu) {
        MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
        try {
            menuService.insert(menu);
            messageObject.ok("菜单信息保存成功", menu);
        } catch (Exception e) {
            messageObject.error("菜单信息保存失败");
        } 
        return messageObject;
    }

    /**
     * 修改页面
     */
    @Authority(alias = "menu-edit")
    @RequestMapping(value = "/platform/menu/menu-edit/{id}.do", method = RequestMethod.GET)
    public String menuEdit(@PathVariable(value = "id") String id, Model model, HttpServletRequest request) {
        try {
            Menu menu = menuService.get(id);
            model.addAttribute("menu", menu);
            Map<String, Object> paramsMap = RequestData.getRequestDataToMap(request);
            paramsMap.put("status", SysConstant.DataStatus.VALID);
            List<Menu> menuList = menuService.queryListByMap(paramsMap);
            List<MenuTree> menuTrees = Lists.newArrayList();
            for (Menu m : menuList) {
                menuTrees.add(new MenuTree(m));
            }
            model.addAttribute("parent", menuService.get(menu.getMenuId()));
            model.addAttribute("menuList", JsonMapper.toJson(menuTrees));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "module/platform/menu/menu-edit";
    }

    /**
     * 更新数据到数据库
     */
    @ResponseBody
    @RequestMapping(value = "/platform/menu/menu-update.json", method = RequestMethod.POST, produces = "application/json")
    @OperateLog(message = "修改菜单信息", optType = OperateType.OptType.UPDATE, service = MenuService.class)
    public MessageObject menuupdate(Menu menu) {
        MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
        try {
            menuService.update(menu);
            messageObject.ok("修改信息成功", menu);
        } catch (Exception e) {
            e.printStackTrace();
            messageObject.error("修改信息失败");
        } 
        return messageObject;

    }

    /**
     * 从数据库删除数据
     */
    @ResponseBody
    @Authority(alias = "menu-delete")
    @RequestMapping(value = "/platform/menu/menu-delete/{id}.json", method = RequestMethod.POST)
    @OperateLog(message = "删除菜单信息", optType = OperateType.OptType.DELETE, service = MenuService.class)
    public MessageObject menuDelete(@PathVariable(value = "id") String id) {
        MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
        try {
            String[] ids = id.split(",");
            List<String> list = Arrays.asList(ids);
            menuService.deleteBatch(list);
            messageObject.ok("删除信息成功", null);
        } catch (Exception e) {
            messageObject.error("删除信息失败");
        }
        return messageObject;
    }

    /**
     * 列表页面
     */
    @Authority(alias = "menu-mgt")
    @RequestMapping(value = "/platform/menu/menu-list.do", method = RequestMethod.GET)
    public String menuList() {
        return "module/platform/menu/menu-list";
    }

    /**
     * 获取列表数据
     */
    @ResponseBody
    @RequestMapping(value = "/platform/menu/menu-list.json", method = RequestMethod.POST)
    public MessageObject menuList(HttpServletRequest request, PageSupport support) {
        MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
        try {
            Map<String, Object> paramsMap = RequestData.getParameterMap(request);
            PagerInfo<Menu> pagerInfo = menuService.queryPage(paramsMap, new PageRowBounds(support));
            messageObject.ok("查询菜单列表信息成功", pagerInfo);
        } catch (ServiceException e) {
            e.printStackTrace();
            messageObject.error("查询菜单信息失败");
        } 
        return messageObject;
    }
}
