package com.mybatis.platform.role.service.impl;

import org.springframework.stereotype.Service;

import com.mybatis.core.orm.common.service.impl.BaseServiceImpl;
import com.mybatis.platform.role.dao.RoleMapper;
import com.mybatis.platform.role.entity.Role;
import com.mybatis.platform.role.service.RoleService;

@Service
public class RoleServiceImpl extends BaseServiceImpl<Role, String, RoleMapper> implements RoleService{

}
