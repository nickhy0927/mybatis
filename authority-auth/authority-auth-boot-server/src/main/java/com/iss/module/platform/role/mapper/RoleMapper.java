package com.iss.module.platform.role.mapper;

import java.util.List;

import com.iss.module.platform.role.entity.Role;

public interface RoleMapper {

	List<Role> queryRoleList(String userId);
}
