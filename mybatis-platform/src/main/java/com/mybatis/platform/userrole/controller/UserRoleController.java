package com.mybatis.platform.userrole.controller;

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

import com.mybatis.platform.userrole.entity.UserRole;
import com.mybatis.platform.userrole.service.UserRoleService;
import com.mybatis.common.utils.MessageObject;
import com.mybatis.common.utils.PageSupport;
import com.mybatis.common.utils.PagerInfo;
import com.mybatis.common.utils.RequestData;

/**
 * @Title: userRoleController.java
 * @Package com.mybatis.platform.userRole.controller
 * @Description 用户角色管理
 * @author yuanhuangd
 * @version V1.0
 * @Date: 2018年6月9日 下午6:03:46
 */
@Controller
public class UserRoleController {

	@Autowired
	private UserRoleService userRoleService;

	/**
	 * 新增页面
	 */
	@RequestMapping(value = "/platform/userrole/user-role-create.do", method = RequestMethod.GET)
	public String userRoleCreate() {
		return "module/platform/userrole/user-role-create";
	}
	
	/**
	 * 新增数据到数据库
	 */
	@ResponseBody
    @RequestMapping(value = "/platform/userrole/user-role-save.json", method = RequestMethod.POST)
    public MessageObject userRoleSave(UserRole userRole) {
        MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
        try {
            userRole = userRoleService.insert(userRole);
            messageObject.ok("保存信息成功", userRole);
        } catch (Exception e) {
            messageObject.error("保存信息失败");
        }
        return messageObject;
    }
    
    /**
	 * 修改页面
	 */
	@RequestMapping(value = "/platform/userrole/user-role-edit/{id}.do", method = RequestMethod.GET)
	public String userRoleEdit(@PathVariable(value = "id") String id, Model model) {
		UserRole userRole = userRoleService.get(id);
		model.addAttribute("userRole", userRole);
		return "module/platform/userrole/user-role-edit";
	}
	
	/**
	 * 更新数据到数据库
	 */
	@ResponseBody
    @RequestMapping(value = "/platform/userrole/user-role-update.json", method = RequestMethod.POST)
    public MessageObject userRoleupdate(UserRole userRole) {
        MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
        try {
            userRoleService.update(userRole);
            messageObject.ok("修改信息成功", userRole);
        } catch (Exception e) {
            messageObject.error("修改信息失败");
        }
        return messageObject;
    }
	
	/**
	 * 从数据库删除数据
	 */
	@ResponseBody
	@RequestMapping(value = "/platform/userrole/user-role-delete/{id}.json", method = RequestMethod.POST)
	public MessageObject userRoleDelete(@PathVariable(value = "id") String id) {
		MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
		try {
			String[] ids = id.split(",");
			List<String> list = Arrays.asList(ids);
			userRoleService.deleteBatch(list);
			messageObject.ok("删除信息成功", null);
		} catch (Exception e) {
			messageObject.error("删除信息失败");
		}
		return messageObject;
	}

	/**
	 * 列表页面
	 */
	@RequestMapping(value = "/platform/userrole/user-role-list.do", method = RequestMethod.GET)
	public String userRoleList() {
		return "module/platform/userrole/user-role-list";
	}

	/**
	 * 获取列表数据
	 */
	@RequestMapping(value = "/platform/userrole/user-role-list.json", method = RequestMethod.POST)
	public void userRoleList(HttpServletRequest request, HttpServletResponse response, PageSupport support) {
		MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
		try {
			Map<String, Object> paramsMap = RequestData.getRequestDataToMap(request);
			PagerInfo<UserRole> pagerInfo = userRoleService.queryPageByMap(paramsMap, support);
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
