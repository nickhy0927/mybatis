package com.mybatis.platform.rolemenu.service;

import com.mybatis.core.orm.common.service.BaseService;
import com.mybatis.platform.rolemenu.dao.RoleMenuMapper;
import com.mybatis.platform.rolemenu.entity.RoleMenu;

import java.util.List;

public interface RoleMenuService extends BaseService<RoleMenu, String, RoleMenuMapper> {

    List<RoleMenu> getRoleMenuList(String roleId);
}
