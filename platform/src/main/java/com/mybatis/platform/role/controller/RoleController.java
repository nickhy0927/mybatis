package com.mybatis.platform.role.controller;

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

import com.mybatis.common.utils.MessageObject;
import com.mybatis.common.utils.PageSupport;
import com.mybatis.common.utils.PagerInfo;
import com.mybatis.common.utils.RequestData;
import com.mybatis.core.orm.entity.PageRowBounds;
import com.mybatis.platform.role.entity.Role;
import com.mybatis.platform.role.service.RoleService;

/**
 * @Title: roleController.java
 * @Package com.mybatis.platform.role.controller
 * @Description TODO(用一句话描述该文件做什么)
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
	@RequestMapping(value = "/platform/role/role-create.do", method = RequestMethod.GET)
	public String roleCreate() {
		return "module/platform/role/role-create";
	}
	
	/**
	 * 新增数据到数据库
	 */
	@ResponseBody
    @RequestMapping(value = "/platform/role/role-save.json", method = RequestMethod.POST)
    public MessageObject roleSave(Role role) {
        MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
        try {
            roleService.insert(role);
            messageObject.ok("保存信息成功", role);
        } catch (Exception e) {
            messageObject.error("保存信息失败");
        }
        return messageObject;
    }
    
    /**
	 * 修改页面
	 */
	@RequestMapping(value = "/platform/role/role-edit/{id}.do", method = RequestMethod.GET)
	public String roleEdit(@PathVariable(value = "id") String id, Model model) {
		Role role = roleService.get(id);
		model.addAttribute("role", role);
		return "module/platform/role/role-edit";
	}
	
	/**
	 * 更新数据到数据库
	 */
	@ResponseBody
    @RequestMapping(value = "/platform/role/role-update.json", method = RequestMethod.POST)
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
	@RequestMapping(value = "/platform/role/role-delete/{id}.json", method = RequestMethod.POST)
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
	@RequestMapping(value = "/platform/role/role-list.json", method = RequestMethod.POST)
	public void roleList(HttpServletRequest request, HttpServletResponse response, PageSupport support) {
		MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
		try {
			Map<String, Object> paramsMap = RequestData.getRequestDataToMap(request);
			PagerInfo<Role> pagerInfo = roleService.queryPage(paramsMap, new PageRowBounds(support));
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