package com.mybatis.platform.user.controller;

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

import com.mybatis.common.utils.MessageObject;
import com.mybatis.common.utils.PageSupport;
import com.mybatis.common.utils.PagerInfo;
import com.mybatis.common.utils.RequestData;
import com.mybatis.core.orm.entity.PageRowBounds;
import com.mybatis.platform.user.entity.User;
import com.mybatis.platform.user.service.UserService;

/**
 * @Title: userController.java
 * @Package com.mybatis.platform.user.controller
 * @Description 用户管理
 * @author yuanhuangd
 * @version V1.0
 * @Date: 2018年6月9日 下午6:03:46
 */
@Controller
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * 新增页面
	 */
	@RequestMapping(value = "/platform/user/user-create.do", method = RequestMethod.GET)
	public String userCreate() {
		return "module/platform/user/user-create";
	}
	
	/**
	 * 新增数据到数据库
	 */
	@ResponseBody
    @RequestMapping(value = "/platform/user/user-save.json", method = RequestMethod.POST)
    public MessageObject userSave(User user) {
        MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
        try {
            userService.insert(user);
            messageObject.ok("保存用户信息成功", user);
        } catch (Exception e) {
            messageObject.error("保存用户信息失败");
        }
        return messageObject;
    }
    
    /**
	 * 修改页面
	 */
	@RequestMapping(value = "/platform/user/user-edit/{id}.do", method = RequestMethod.GET)
	public String userEdit(@PathVariable(value = "id") String id, Model model) {
		User user = userService.get(id);
		model.addAttribute("user", user);
		return "module/platform/user/user-edit";
	}
	
	/**
	 * 更新数据到数据库
	 */
	@ResponseBody
    @RequestMapping(value = "/platform/user/user-update.json", method = RequestMethod.POST)
    public MessageObject userupdate(User user) {
        MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
        try {
            userService.update(user);
            messageObject.ok("修改用户信息成功", user);
        } catch (Exception e) {
        	e.printStackTrace();
            messageObject.error("修改用户信息失败");
        }
        return messageObject;
    }
	
	/**
	 * 从数据库删除数据
	 */
	@ResponseBody
	@RequestMapping(value = "/platform/user/user-delete/{id}.json", method = RequestMethod.POST)
	public MessageObject userDelete(@PathVariable(value = "id") String id) {
		MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
		try {
			String[] ids = id.split(",");
			List<String> list = Arrays.asList(ids);
			userService.deleteBatch(list);
			messageObject.ok("删除用户信息成功", null);
		} catch (Exception e) {
			messageObject.error("删除用户信息失败");
		}
		return messageObject;
	}

	/**
	 * 列表页面
	 */
	@RequestMapping(value = "/platform/user/user-list.do", method = RequestMethod.GET)
	public String userList() {
		return "module/platform/user/user-list";
	}

	/**
	 * 获取列表数据
	 */
	@ResponseBody
	@RequestMapping(value = "/platform/user/user-list.json", method = RequestMethod.POST)
	public MessageObject userList(HttpServletRequest request, PageSupport support) {
		MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
		try {
			Map<String, Object> paramsMap = RequestData.getRequestDataToMap(request);
			PagerInfo<User> pagerInfo = userService.queryPage(paramsMap, new PageRowBounds(support));
			messageObject.ok("获取用户信息成功", pagerInfo);
		} catch (Exception e) {
			e.printStackTrace();
			messageObject.error("获取用户信息异常");
		} 
		return messageObject;
	}
}
