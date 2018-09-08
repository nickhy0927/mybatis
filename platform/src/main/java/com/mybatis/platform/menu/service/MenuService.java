package com.mybatis.platform.menu.service;

import com.mybatis.core.orm.common.service.BaseService;
import com.mybatis.platform.menu.dao.MenuMapper;
import com.mybatis.platform.menu.entity.Menu;
import com.mybatis.platform.menu.entity.MenuTemplate;
import com.mybatis.platform.role.entity.Role;

import java.util.List;

public interface MenuService extends BaseService<Menu, String, MenuMapper> {

    List<MenuTemplate> queryIndexMenu();

    /**
     * 根据角色查询菜单信息
     * @param roleList
     * @return
     */
    List<Menu> queryMenuByRoleList(List<Role> roleList);
}
