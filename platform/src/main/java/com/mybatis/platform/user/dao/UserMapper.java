package com.mybatis.platform.user.dao;

import com.mybatis.platform.user.entity.User;
import com.mybatis.core.orm.common.mapper.BaseMapper;

public interface UserMapper extends BaseMapper<User,String> {

    User login(String loginName);
}