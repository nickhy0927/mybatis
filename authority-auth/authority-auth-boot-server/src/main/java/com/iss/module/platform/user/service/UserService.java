package com.iss.module.platform.user.service;

import java.util.List;

import com.iss.module.platform.user.pojo.User;

public interface UserService {

	User login(String loginName);

	List<User> queryPageByMap();
}
