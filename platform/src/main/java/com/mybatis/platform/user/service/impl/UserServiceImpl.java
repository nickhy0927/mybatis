package com.mybatis.platform.user.service.impl;

import com.mybatis.common.utils.MD5;
import com.mybatis.config.database.entity.UserDatabase;
import com.mybatis.config.database.service.UserDatabaseService;
import com.mybatis.core.orm.core.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mybatis.core.orm.common.service.impl.BaseServiceImpl;
import com.mybatis.platform.user.dao.UserMapper;
import com.mybatis.platform.user.entity.User;
import com.mybatis.platform.user.service.UserService;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, String, UserMapper> implements UserService{

    @Autowired
    private UserDatabaseService userDatabaseService;

    @Override
    public int insert(User t) throws ServiceException {
        t.setPassword(MD5.MD5Encode(t.getPassword()).toUpperCase());
        UserDatabase userDatabase = new UserDatabase();
        userDatabase.setDatabaseId(1 + "");
        int num = super.insert(t);
        userDatabase.setUserId(t.getId());
        userDatabaseService.insert(userDatabase);
        return num;
    }
}
