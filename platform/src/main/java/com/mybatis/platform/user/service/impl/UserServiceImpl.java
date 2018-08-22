package com.mybatis.platform.user.service.impl;

import org.springframework.stereotype.Service;

import com.mybatis.core.orm.common.service.impl.BaseServiceImpl;
import com.mybatis.platform.user.dao.UserMapper;
import com.mybatis.platform.user.entity.User;
import com.mybatis.platform.user.service.UserService;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, String, UserMapper> implements UserService{

}
