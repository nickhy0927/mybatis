package com.mybatis.platform.userrole.service.impl;

import org.springframework.stereotype.Service;

import com.mybatis.core.orm.common.service.impl.BaseServiceImpl;
import com.mybatis.platform.userrole.dao.UserRoleMapper;
import com.mybatis.platform.userrole.entity.UserRole;
import com.mybatis.platform.userrole.service.UserRoleService;

@Service
public class UserRoleServiceImpl extends BaseServiceImpl<UserRole, String, UserRoleMapper> implements UserRoleService{

}
