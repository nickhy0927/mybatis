package com.mybatis.platform.role.service;

import com.mybatis.core.orm.common.service.BaseService;
import com.mybatis.platform.role.dao.RoleMapper;
import com.mybatis.platform.role.entity.Role;

import java.util.List;

public interface RoleService extends BaseService<Role, String, RoleMapper> {

    /**
     * 根据用户查角色
     * @param userId
     * @return
     */
    List<Role> queryRoleByUserId(String userId);
}
