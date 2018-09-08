package com.mybatis.platform.rolemenu.controller;

import com.google.common.collect.Lists;
import com.mybatis.common.utils.*;
import com.mybatis.core.orm.constant.SysConstant;
import com.mybatis.interceptor.Authority;
import com.mybatis.interceptor.MessageResources;
import com.mybatis.interceptor.OperateLog;
import com.mybatis.interceptor.OperateType;
import com.mybatis.platform.menu.entity.Menu;
import com.mybatis.platform.menu.entity.MenuTree;
import com.mybatis.platform.menu.service.MenuService;
import com.mybatis.platform.role.entity.Role;
import com.mybatis.platform.role.service.RoleService;
import com.mybatis.platform.rolemenu.entity.RoleMenu;
import com.mybatis.platform.rolemenu.service.RoleMenuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author yuanhuangd
 * @version V1.0
 * @Title: roleMenuController.java
 * @Package com.mybatis.platform.roleMenu.controller
 * @Description 角色菜单管理
 * @Date: 2018年6月9日 下午6:03:46
 */
@Controller
public class RoleMenuController {

    @Autowired
    private RoleMenuService roleMenuService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleService roleService;

    /**
     * 新增页面
     */
    @Authority(alias = "role-menu-create")
    @RequestMapping(value = "/platform/rolemenu/role-menu-create.do", method = RequestMethod.GET)
    public String roleMenuCreate(ModelMap modelMap, @RequestParam(value = "id") String id) {
        Menu menu = new Menu();
        menu.setEnable(SysConstant.Enable.YES);
        Role role = roleService.get(id);
        modelMap.put("role", role);
        modelMap.put("defaultValue", JsonMapper.toJson(roleMenuService.getRoleMenuList(id)));
        List<Menu> menus = menuService.queryListByObject(menu);
        List<MenuTree> menuTrees = Lists.newArrayList();
        for (Menu m : menus)
            menuTrees.add(new MenuTree(m));
        modelMap.put("menuList", JsonMapper.toJson(menuTrees));
        return "module/platform/rolemenu/role-menu-create";
    }

    /**
     * 新增数据到数据库
     */
    @ResponseBody
    @RequestMapping(value = "/platform/rolemenu/role-menu-save.json", method = RequestMethod.POST)
    @OperateLog(message = "新增角色菜单信息", optType = OperateType.OptType.INSERT, service = RoleMenuService.class)
    public MessageObject roleMenuSave(String roleId, String menuIds) {
        MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
        List<RoleMenu> roleMenus = Lists.newArrayList();
        try {
            if (StringUtils.isNotEmpty(menuIds)) {
                String[] strings = menuIds.split(",");
                for (String menuId: strings) {
                    RoleMenu roleMenu = new RoleMenu();
                    roleMenu.setMenuId(menuId);
                    roleMenu.setRoleId(roleId);
                    roleMenus.add(roleMenu);
                }
            }
            roleMenuService.delete(roleId);
            roleMenuService.insertBatch(roleMenus);
            messageObject.ok(MessageResources.getMessage("RoleMenu.save.success"), roleMenus);
        } catch (Exception e) {
            messageObject.error(MessageResources.getMessage("RoleMenu.save.fail"));
        }
        return messageObject;
    }

    /**
     * 修改页面
     */
    @Authority(alias = "role-menu-edit")
    @RequestMapping(value = "/platform/rolemenu/role-menu-edit/{id}.do", method = RequestMethod.GET)
    public String roleMenuEdit(@PathVariable(value = "id") String id, Model model) {
        RoleMenu roleMenu = roleMenuService.get(id);
        model.addAttribute("roleMenu", roleMenu);
        return "module/platform/rolemenu/role-menu-edit";
    }

    /**
     * 从数据库删除数据
     */
    @ResponseBody
    @Authority(alias = "role-menu-delete")
    @RequestMapping(value = "/platform/rolemenu/role-menu-delete/{id}.json", method = RequestMethod.POST)
    @OperateLog(message = "删除角色菜单信息", optType = OperateType.OptType.DELETE, service = RoleMenuService.class)
    public MessageObject roleMenuDelete(@PathVariable(value = "id") String id) {
        MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
        try {
            String[] ids = id.split(",");
            List<String> list = Arrays.asList(ids);
            roleMenuService.deleteBatch(list);
            messageObject.ok("删除信息成功", null);
        } catch (Exception e) {
            messageObject.error("删除信息失败");
        }
        return messageObject;
    }

    /**
     * 列表页面
     */
    @Authority(alias = "role-menu-mgt")
    @RequestMapping(value = "/platform/rolemenu/role-menu-list.do", method = RequestMethod.GET)
    public String roleMenuList() {
        return "module/platform/rolemenu/role-menu-list";
    }

    /**
     * 获取列表数据
     */
    @ResponseBody
    @RequestMapping(value = "/platform/rolemenu/role-menu-list.json", method = RequestMethod.POST)
    public MessageObject roleMenuList(HttpServletRequest request, HttpServletResponse response, PageSupport support) {
        MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
        try {
            Map<String, Object> paramsMap = RequestData.getRequestDataToMap(request);
            PagerInfo<RoleMenu> pagerInfo = roleMenuService.queryPageByMap(paramsMap, support);
            messageObject.ok("获取模版输出成功", pagerInfo);
        } catch (IOException e) {
            e.printStackTrace();
            messageObject.error("获取模版数据异常");
        }
        return messageObject;
    }
}
