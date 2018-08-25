package com.mybatis.platform.menu.controller;

import com.google.common.collect.Lists;
import com.mybatis.common.utils.*;
import com.mybatis.core.orm.constant.SysConstant;
import com.mybatis.core.orm.entity.PageRowBounds;
import com.mybatis.platform.menu.entity.Menu;
import com.mybatis.platform.menu.entity.MenuTree;
import com.mybatis.platform.menu.service.MenuService;
import com.mybatis.utils.NumberCreate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author yuanhuangd
 * @version V1.0
 * @Title: menuController.java
 * @Package com.mybatis.platform.menu.controller
 * @Description TODO(用一句话描述该文件做什么)
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
    @RequestMapping(value = "/platform/menu/menu-save.json", method = RequestMethod.POST, produces = "application/json")
    public void menuSave(Menu menu) {
        MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
        try {
            menuService.insert(menu);
            messageObject.ok("菜单信息保存成功", menu);
        } catch (Exception e) {
            messageObject.error("菜单信息保存失败");
        } finally {
            try {
                messageObject.returnData(messageObject);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 修改页面
     */
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
    @RequestMapping(value = "/platform/menu/menu-update.json", method = RequestMethod.POST, produces = "application/json")
    public void menuupdate(Menu menu) {
        MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
        try {
            menuService.update(menu);
            messageObject.ok("修改信息成功", menu);
        } catch (Exception e) {
            e.printStackTrace();
            messageObject.error("修改信息失败");
        } finally {
            try {
                messageObject.returnData(messageObject);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 从数据库删除数据
     */
    @ResponseBody
    @RequestMapping(value = "/platform/menu/menu-delete/{id}.json", method = RequestMethod.POST)
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
    @RequestMapping(value = "/platform/menu/menu-list.do", method = RequestMethod.GET)
    public String menuList() {
        return "module/platform/menu/menu-list";
    }

    /**
     * 获取列表数据
     */
    @RequestMapping(value = "/platform/menu/menu-list.json", method = RequestMethod.POST)
    public void menuList(HttpServletRequest request, PageSupport support) {
        MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
        try {
            Map<String, Object> paramsMap = RequestData.getRequestDataToMap(request);
            PagerInfo<Menu> pagerInfo = menuService.queryPage(paramsMap, new PageRowBounds(support));
            messageObject.ok("获取模版输出成功", pagerInfo);
        } catch (IOException e) {
            e.printStackTrace();
            messageObject.error("获取模版数据异常");
        } finally {
            try {
                messageObject.returnData(messageObject);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
