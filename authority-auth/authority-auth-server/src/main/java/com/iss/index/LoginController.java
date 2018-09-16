package com.iss.index;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.iss.platform.user.entity.UserInfo;
import com.iss.platform.user.service.UserInfoService;
import com.iss.utils.ResponesEntity;
import com.mybatis.utils.MessageObject;

@Controller
public class LoginController {

	@Autowired
	private UserInfoService userInfoService;

	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public String login(HttpServletRequest request) {
		return "login";
	}

	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public void login() {
		MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
		ResponesEntity<UserInfo> responesEntity = userInfoService.findUserInfoByLoginname("yangzi", "q1w2e3");
		if (responesEntity.getCode() == ResponesEntity.ResultCode.SUCCESS) {
			messageObject.setObject(responesEntity.getData());
		} else {
			messageObject.setErrorMessage("登录失败");
		}
	}
}
