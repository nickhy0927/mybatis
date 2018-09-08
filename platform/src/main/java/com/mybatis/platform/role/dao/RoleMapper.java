package com.mybatis.platform.role.dao;

import com.mybatis.platform.role.entity.Role;
import com.mybatis.core.orm.common.mapper.BaseMapper;

import java.util.List;

public interface RoleMapper extends BaseMapper<Role,String> {

    /**
     * 根据用户查角色
     * @param userId
     * @return
     */
    List<Role> queryRoleByUserId(String userId);
}