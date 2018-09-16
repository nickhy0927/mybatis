package com.iss.module.platform.role.service;

import java.util.List;

import com.iss.module.platform.role.entity.Role;

public interface RoleService {

	List<Role> queryRoleList(String userId);
}
