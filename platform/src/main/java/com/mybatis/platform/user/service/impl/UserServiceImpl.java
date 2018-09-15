package com.mybatis.platform.user.service.impl;

import com.mybatis.common.utils.MD5;
import com.mybatis.core.orm.common.service.impl.BaseServiceImpl;
import com.mybatis.core.orm.core.exception.ServiceException;
import com.mybatis.platform.user.dao.UserMapper;
import com.mybatis.platform.user.entity.User;
import com.mybatis.platform.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, String, UserMapper> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int insert(User t) throws ServiceException {
        try {
            t.setPassword(MD5.MD5Encode(t.getPassword()).toUpperCase());
            int num = super.insert(t);
            return num;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("新增用户信息异常");
        }
    }

    @Override
    public User login(String loginName) {
        try {
            return userMapper.login(loginName);
        } catch (ServiceException e) {
            throw new ServiceException("查询账户为" + loginName + "的账号出现异常", new Throwable());
        }
    }
}
