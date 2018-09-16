package com.iss.module.platform.role.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iss.module.platform.role.entity.Role;
import com.iss.module.platform.role.mapper.RoleMapper;
import com.iss.module.platform.role.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleMapper roleMapper;
	
	@Override
	public List<Role> queryRoleList(String userId) {
		return roleMapper.queryRoleList(userId);
	}

}
