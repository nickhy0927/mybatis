package com.mybatis.platform.userrole.dao;

import com.mybatis.platform.role.entity.Role;
import com.mybatis.platform.userrole.entity.UserRole;
import com.mybatis.core.orm.common.mapper.BaseMapper;

import java.util.List;

public interface UserRoleMapper extends BaseMapper<UserRole,String> {

    /**
     * 查询用户下的所有角色
     * @param userId
     * @return
     */
    List<UserRole> queryUserRoleList(String userId);

}