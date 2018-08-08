package com.mybatis.platform.user.dao.impl;

import com.mybatis.platform.user.dao.UserDao;
import com.mybatis.platform.user.entity.User;
import com.mybatis.core.orm.core.dao.impl.CommonDaoImpl;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends CommonDaoImpl<User, String> implements UserDao {

}