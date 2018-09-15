package com.iss.index;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iss.platform.user.entity.UserInfo;
import com.iss.platform.user.service.UserInfoService;
import com.iss.utils.ResponesEntity;
import com.mybatis.utils.MessageObject;

@RestController
public class LoginController {

	@Autowired
	private UserInfoService userInfoService;

	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public MessageObject login() {
		MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
		ResponesEntity<UserInfo> responesEntity = userInfoService.findUserInfoByLoginname("yangzi", "q1w2e3");
		if (responesEntity.getCode() == ResponesEntity.ResultCode.SUCCESS) {
			messageObject.setSuccessMessage("登录成功");
			messageObject.setObject(responesEntity.getData());
		} else {
			messageObject.setErrorMessage("登录失败");
		}
		return messageObject;
	}
}
