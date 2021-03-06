package com.mybatis.platform.role.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mybatis.common.utils.*;
import com.mybatis.core.orm.constant.SysConstant;
import com.mybatis.interceptor.Authority;
import com.mybatis.interceptor.OperateLog;
import com.mybatis.interceptor.OperateType;
import com.mybatis.platform.role.entity.Role;
import com.mybatis.platform.role.entity.RoleTree;
import com.mybatis.platform.role.service.RoleService;
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
 * @Title: roleController.java
 * @Package com.mybatis.platform.role.controller
 * @Description 角色管理
 * @author yuanhuangd
 * @version V1.0
 * @Date: 2018年6月9日 下午6:03:46
 */
@Controller
public class RoleController {

	@Autowired
	private RoleService roleService;

	/**
	 * 新增页面
	 */
	@Authority(alias = "role-mgt")
	@RequestMapping(value = "/platform/role/role-create.do", method = RequestMethod.GET)
	public String roleCreate(Model model,HttpServletRequest request) {
		try {
			model.addAttribute("code", NumberCreate.generateNumber2());
			Map<String, Object> paramsMap = RequestData.getRequestDataToMap(request);
			paramsMap.put("status", SysConstant.DataStatus.VALID);
			List<Role> roleList = roleService.queryListByMap(paramsMap);
			List<RoleTree> roleTrees = Lists.newArrayList();
			for (Role role : roleList) {
				roleTrees.add(new RoleTree(role));
			}
			model.addAttribute("roleList", new JsonMapper().toJson(roleTrees));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "module/platform/role/role-create";
	}
	
	/**
	 * 新增数据到数据库
	 */
	@ResponseBody
    @RequestMapping(value = "/platform/role/role-save.json", method = RequestMethod.POST)
	@OperateLog(message = "新增角色信息", optType = OperateType.OptType.INSERT, service = RoleService.class)
	public MessageObject roleSave(Role role) {
        MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
        try {
            roleService.insert(role);
            messageObject.ok("保存角色信息成功", role);
        } catch (Exception e) {
            messageObject.error("保存角色信息失败");
        }
        return messageObject;
    }
    
    /**
	 * 修改页面
	 */
	@Authority(alias = "role-create")
	@RequestMapping(value = "/platform/role/role-edit/{id}.do", method = RequestMethod.GET)
	public String roleEdit(@PathVariable(value = "id") String id, Model model) {
        model.addAttribute("code", NumberCreate.generateNumber2());
        Map<String, Object> paramsMap = Maps.newConcurrentMap();
        paramsMap.put("status", SysConstant.DataStatus.VALID);
        List<Role> roleList = roleService.queryListByMap(paramsMap);
        List<RoleTree> roleTrees = Lists.newArrayList();
        for (Role role : roleList) {
            roleTrees.add(new RoleTree(role));
        }
        Role role = roleService.get(id);
        Role parent = roleService.get(role.getRoleId());
        role.setRoleName(parent != null ? parent.getName() : "");
        model.addAttribute("role", role);
        model.addAttribute("roleList", new JsonMapper().toJson(roleTrees));
		return "module/platform/role/role-edit";
	}
	
	/**
	 * 更新数据到数据库
	 */
	@ResponseBody
    @RequestMapping(value = "/platform/role/role-update.json", method = RequestMethod.POST)
	@OperateLog(message = "修改角色信息", optType = OperateType.OptType.UPDATE, service = RoleService.class)
	public MessageObject roleupdate(Role role) {
        MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
        try {
            roleService.update(role);
            messageObject.ok("修改信息成功", role);
        } catch (Exception e) {
            messageObject.error("修改信息失败");
        }
        return messageObject;
    }
	
	/**
	 * 从数据库删除数据
	 */
	@ResponseBody
	@Authority(alias = "role-delete")
	@RequestMapping(value = "/platform/role/role-delete/{id}.json", method = RequestMethod.POST)
	@OperateLog(message = "删除角色信息", optType = OperateType.OptType.DELETE, service = RoleService.class)
	public MessageObject roleDelete(@PathVariable(value = "id") String id) {
		MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
		try {
			String[] ids = id.split(",");
			List<String> list = Arrays.asList(ids);
			roleService.deleteBatch(list);
			messageObject.ok("删除信息成功", null);
		} catch (Exception e) {
			messageObject.error("删除信息失败");
		}
		return messageObject;
	}

	/**
	 * 列表页面
	 */
	@RequestMapping(value = "/platform/role/role-list.do", method = RequestMethod.GET)
	public String roleList() {
		return "module/platform/role/role-list";
	}

	/**
	 * 获取列表数据
	 */
	@ResponseBody
	@RequestMapping(value = "/platform/role/role-list.json", method = RequestMethod.POST)
	public MessageObject roleList(HttpServletRequest request, PageSupport support) {
		MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
		try {
			Map<String, Object> paramsMap = RequestData.getRequestDataToMap(request);
			PagerInfo<Role> pagerInfo = roleService.queryPageByMap(paramsMap, support);
			messageObject.ok("获取角色信息成功", pagerInfo);
		} catch (Exception e) {
			e.printStackTrace();
			messageObject.error("获取角色信息异常");
		} 
		return messageObject;
	}
}
