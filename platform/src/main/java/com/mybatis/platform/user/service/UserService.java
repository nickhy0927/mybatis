package com.mybatis.platform.user.service;

import com.mybatis.core.orm.common.service.BaseService;
import com.mybatis.platform.user.dao.UserMapper;
import com.mybatis.platform.user.entity.User;

public interface UserService extends BaseService<User, String, UserMapper> {

    User login(String loginName);
}
