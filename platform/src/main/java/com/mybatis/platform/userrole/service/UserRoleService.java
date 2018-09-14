package com.mybatis.platform.userrole.service;

import java.util.List;

import com.mybatis.core.orm.common.service.BaseService;
import com.mybatis.platform.userrole.dao.UserRoleMapper;
import com.mybatis.platform.userrole.entity.UserRole;

public interface UserRoleService extends BaseService<UserRole, String, UserRoleMapper> {

    /**
     * 查询用户下的所有角色
     * @param userId
     * @return
     */
    List<UserRole> queryUserRoleList(String userId);

}
