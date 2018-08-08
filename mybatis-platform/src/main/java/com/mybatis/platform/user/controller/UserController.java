package com.mybatis.platform.user.controller;

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

import com.mybatis.platform.user.entity.User;
import com.mybatis.platform.user.service.UserService;
import com.mybatis.common.utils.MessageObject;
import com.mybatis.common.utils.PageSupport;
import com.mybatis.common.utils.PagerInfo;
import com.mybatis.common.utils.RequestData;

/**
 * @Title: userController.java
 * @Package com.mybatis.platform.user.controller
 * @Description TODO(用一句话描述该文件做什么)
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
            user = userService.insert(user);
            messageObject.ok("保存信息成功", user);
        } catch (Exception e) {
            messageObject.error("保存信息失败");
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
            messageObject.ok("修改信息成功", user);
        } catch (Exception e) {
            messageObject.error("修改信息失败");
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
			messageObject.ok("删除信息成功", null);
		} catch (Exception e) {
			messageObject.error("删除信息失败");
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
	@RequestMapping(value = "/platform/user/user-list.json", method = RequestMethod.POST)
	public void userList(HttpServletRequest request, HttpServletResponse response, PageSupport support) {
		MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
		try {
			Map<String, Object> paramsMap = RequestData.getRequestDataToMap(request);
			PagerInfo<User> pagerInfo = userService.queryPageByMap(paramsMap, support);
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
