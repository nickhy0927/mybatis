package com.mybatis.platform.rolemenu.dao;

import com.mybatis.core.orm.common.mapper.BaseMapper;
import com.mybatis.platform.rolemenu.entity.RoleMenu;

import java.util.List;

public interface RoleMenuMapper extends BaseMapper<RoleMenu,String> {

    List<RoleMenu> getRoleMenuList(String roleId);
}