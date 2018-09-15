package com.iss.module.platform.user.mapper;

import java.util.List;

import com.iss.module.platform.user.pojo.User;

public interface UserMapper {

	User login(String loginName);
	
	List<User> queryPageByMap();
}
