package com.mybatis.platform.rolemenu.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mybatis.platform.rolemenu.entity.RoleMenu;
import com.mybatis.platform.rolemenu.service.RoleMenuService;
import com.mybatis.common.utils.MessageObject;
import com.mybatis.common.utils.PageSupport;
import com.mybatis.common.utils.PagerInfo;
import com.mybatis.common.utils.RequestData;

/**
 * @Title: roleMenuController.java
 * @Package com.mybatis.platform.roleMenu.controller
 * @Description 角色菜单管理
 * @author yuanhuangd
 * @version V1.0
 * @Date: 2018年6月9日 下午6:03:46
 */
@Controller
public class RoleMenuController {

	@Autowired
	private RoleMenuService roleMenuService;

	/**
	 * 新增页面
	 */
	@RequestMapping(value = "/platform/rolemenu/role-menu-create.do", method = RequestMethod.GET)
	public String roleMenuCreate() {
		return "module/platform/rolemenu/role-menu-create";
	}
	
	/**
	 * 新增数据到数据库
	 */
	@ResponseBody
    @RequestMapping(value = "/platform/rolemenu/role-menu-save.json", method = RequestMethod.POST)
    public MessageObject roleMenuSave(RoleMenu roleMenu) {
        MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
        try {
            roleMenu = roleMenuService.insert(roleMenu);
            messageObject.ok("保存信息成功", roleMenu);
        } catch (Exception e) {
            messageObject.error("保存信息失败");
        }
        return messageObject;
    }
    
    /**
	 * 修改页面
	 */
	@RequestMapping(value = "/platform/rolemenu/role-menu-edit/{id}.do", method = RequestMethod.GET)
	public String roleMenuEdit(@PathVariable(value = "id") String id, Model model) {
		RoleMenu roleMenu = roleMenuService.get(id);
		model.addAttribute("roleMenu", roleMenu);
		return "module/platform/rolemenu/role-menu-edit";
	}
	
	/**
	 * 更新数据到数据库
	 */
	@ResponseBody
    @RequestMapping(value = "/platform/rolemenu/role-menu-update.json", method = RequestMethod.POST)
    public MessageObject roleMenuupdate(RoleMenu roleMenu) {
        MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
        try {
            roleMenuService.update(roleMenu);
            messageObject.ok("修改信息成功", roleMenu);
        } catch (Exception e) {
            messageObject.error("修改信息失败");
        }
        return messageObject;
    }
	
	/**
	 * 从数据库删除数据
	 */
	@ResponseBody
	@RequestMapping(value = "/platform/rolemenu/role-menu-delete/{id}.json", method = RequestMethod.POST)
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
	@RequestMapping(value = "/platform/rolemenu/role-menu-list.do", method = RequestMethod.GET)
	public String roleMenuList() {
		return "module/platform/rolemenu/role-menu-list";
	}

	/**
	 * 获取列表数据
	 */
	@RequestMapping(value = "/platform/rolemenu/role-menu-list.json", method = RequestMethod.POST)
	public void roleMenuList(HttpServletRequest request, HttpServletResponse response, PageSupport support) {
		MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
		try {
			Map<String, Object> paramsMap = RequestData.getRequestDataToMap(request);
			PagerInfo<RoleMenu> pagerInfo = roleMenuService.queryPageByMap(paramsMap, support);
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
