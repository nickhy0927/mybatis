package com.mybatis.platform.menu.service.impl;

import java.util.List;

import com.mybatis.platform.role.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.mybatis.core.orm.common.service.impl.BaseServiceImpl;
import com.mybatis.platform.menu.dao.MenuMapper;
import com.mybatis.platform.menu.entity.Menu;
import com.mybatis.platform.menu.entity.MenuTemplate;
import com.mybatis.platform.menu.service.MenuService;

@Service
public class MenuServiceImpl extends BaseServiceImpl<Menu, String, MenuMapper> implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<MenuTemplate> queryIndexMenu() {
        List<Menu> menus = menuMapper.queryMenuByParentIsNull();
        List<MenuTemplate> menuTemplates = Lists.newArrayList();
        for (Menu menu : menus) {
            menuTemplates.add(new MenuTemplate(menu, queryMenu(menu)));
        }
        return menuTemplates;
    }

    private List<MenuTemplate> queryMenu(Menu menu) {
        List<MenuTemplate> templates = Lists.newArrayList();
        if (menu != null) {
            List<Menu> menus = menuMapper.queryMenuByMenuId(menu);
            for (Menu m : menus) {
                templates.add(new MenuTemplate(m, queryMenu(m)));
            }
        }
        return templates;
    }

    @Override
    public List<Menu> queryMenuByRoleList(List<Role> roleList) {
        return menuMapper.queryMenuByRoleList(roleList);
    }
}
