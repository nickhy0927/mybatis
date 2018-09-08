package com.mybatis.platform.role.service.impl;

import com.mybatis.core.orm.common.service.impl.BaseServiceImpl;
import com.mybatis.platform.role.dao.RoleMapper;
import com.mybatis.platform.role.entity.Role;
import com.mybatis.platform.role.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl extends BaseServiceImpl<Role, String, RoleMapper> implements RoleService{

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> queryRoleByUserId(String userId) {
        return roleMapper.queryRoleByUserId(userId);
    }
}
