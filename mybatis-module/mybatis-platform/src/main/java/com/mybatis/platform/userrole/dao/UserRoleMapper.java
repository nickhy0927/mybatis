package com.mybatis.platform.userrole.dao;

import java.util.List;

import com.mybatis.core.orm.common.mapper.BaseMapper;
import com.mybatis.platform.userrole.entity.UserRole;

public interface UserRoleMapper extends BaseMapper<UserRole,String> {

    /**
     * 查询用户下的所有角色
     * @param userId
     * @return
     */
    List<UserRole> queryUserRoleList(String userId);

}