package com.iss.module.platform.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iss.module.platform.user.mapper.UserMapper;
import com.iss.module.platform.user.pojo.User;
import com.iss.module.platform.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User login(String loginName) {
		return userMapper.login(loginName);
	}

	@Override
	public List<User> queryPageByMap() {
		return userMapper.queryPageByMap();
	}

}
