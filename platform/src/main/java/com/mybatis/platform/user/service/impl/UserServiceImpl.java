package com.mybatis.platform.user.service.impl;

import com.mybatis.common.utils.MD5;
import com.mybatis.core.orm.core.exception.ServiceException;
import org.springframework.stereotype.Service;

import com.mybatis.core.orm.common.service.impl.BaseServiceImpl;
import com.mybatis.platform.user.dao.UserMapper;
import com.mybatis.platform.user.entity.User;
import com.mybatis.platform.user.service.UserService;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, String, UserMapper> implements UserService{

    @Override
    public int insert(User t) throws ServiceException {
        t.setPassword(MD5.MD5Encode(t.getPassword()).toUpperCase());
        return super.insert(t);
    }
}
