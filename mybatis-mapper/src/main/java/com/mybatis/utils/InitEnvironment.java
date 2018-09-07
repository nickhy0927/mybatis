package com.mybatis.utils;

import com.google.common.collect.Maps;
import com.mybatis.common.utils.JedisUtil;
import com.mybatis.core.orm.constant.SysConstant;
import com.mybatis.platform.user.entity.User;
import com.mybatis.platform.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class InitEnvironment {

	@Autowired
	private UserService userService;
	
	public void init() {
		initUser();
	}
	
	private void initUser() {
		Map<String, Object> arg0 = Maps.newConcurrentMap();
		arg0.put("status", SysConstant.DataStatus.VALID);
		List<User> users = userService.queryListByMap(arg0 );
		for (User user : users) {
			JedisUtil.setObject(user.getLoginName(), user, 0);
		}
	}
}
