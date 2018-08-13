package com.mybatis.platform.menu.controller;

import com.google.common.collect.Lists;
import com.mybatis.common.utils.JsonMapper;
import com.mybatis.common.utils.MessageObject;
import com.mybatis.common.utils.PageSupport;
import com.mybatis.common.utils.PagerInfo;
import com.mybatis.common.utils.RequestData;
import com.mybatis.core.orm.constant.SysConstant;
import com.mybatis.platform.menu.entity.Menu;
import com.mybatis.platform.menu.entity.MenuTemplate;
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
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
    @RequestMapping(value = "/platform/menu/menu-save.json", method = RequestMethod.POST)
    public MessageObject menuSave(Menu menu) {
        MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
        try {
            menu = menuService.insert(menu);
            messageObject.ok("保存信息成功", menu);
        } catch (Exception e) {
            messageObject.error("保存信息失败");
        }
        return messageObject;
    }

    @RequestMapping(value = "/platform/menu/query-menu.json", method = RequestMethod.POST)
    public void queryMenu(HttpServletRequest request, HttpServletResponse response) {
        MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
        try {
            Map<String, Object> dataToMap = RequestData.getRequestDataToMap(request);
            dataToMap.put("status", SysConstant.DataStatus.VALID);
            List<MenuTemplate> menuList = menuService.queryMenuByTree(dataToMap);
            for (MenuTemplate menuTemplate : menuList) {
                menuTemplate.setChildren(queryMenuByTree(dataToMap, menuTemplate));
            }
            messageObject.ok("保存信息成功", menuList);
        } catch (Exception e) {
            e.printStackTrace();
            messageObject.error("保存信息失败");
        } finally {
            try {
                messageObject.returnData(response, messageObject);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private List<MenuTemplate> queryMenuByTree(Map<String, Object> dataToMap, MenuTemplate menuTemplate) {
        dataToMap.put("menuId", menuTemplate.getId());
        List<MenuTemplate> menuList = menuService.queryMenuByTree(dataToMap);
        if (menuList != null && menuList.size() > 0) {
            for (MenuTemplate template : menuList) {
                menuTemplate.setChildren(queryMenuByTree(dataToMap, template));
            }
        }
        return menuList;
    }

    /**
     * 修改页面
     */
    @RequestMapping(value = "/platform/menu/menu-edit/{id}.do", method = RequestMethod.GET)
    public String menuEdit(@PathVariable(value = "id") String id, Model model) {
        Menu menu = menuService.get(id);
        model.addAttribute("menu", menu);
        return "module/platform/menu/menu-edit";
    }

    /**
     * 更新数据到数据库
     */
    @ResponseBody
    @RequestMapping(value = "/platform/menu/menu-update.json", method = RequestMethod.POST)
    public MessageObject menuUpdate(Menu menu) {
        MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
        try {
            menuService.update(menu);
            messageObject.ok("修改信息成功", null);
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
    public void menuList(HttpServletRequest request, HttpServletResponse response, PageSupport support) {
        MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
        try {
            Map<String, Object> paramsMap = RequestData.getRequestDataToMap(request);
            PagerInfo<Menu> pagerInfo = menuService.queryPageByMap(paramsMap, support);
            messageObject.ok("获取模版输出成功", pagerInfo);
        } catch (IOException e) {
            e.printStackTrace();
            messageObject.error("获取模版数据异常");
        } finally {
            try {
                messageObject.returnData(response, messageObject);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
