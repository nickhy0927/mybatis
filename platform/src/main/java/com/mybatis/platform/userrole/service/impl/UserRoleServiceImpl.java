package com.mybatis.platform.userrole.service.impl;

import com.mybatis.platform.role.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mybatis.core.orm.common.service.impl.BaseServiceImpl;
import com.mybatis.platform.userrole.dao.UserRoleMapper;
import com.mybatis.platform.userrole.entity.UserRole;
import com.mybatis.platform.userrole.service.UserRoleService;

import java.util.List;

@Service
public class UserRoleServiceImpl extends BaseServiceImpl<UserRole, String, UserRoleMapper> implements UserRoleService{

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public List<UserRole> queryUserRoleList(String userId) {
        return userRoleMapper.queryUserRoleList(userId);
    }

}


