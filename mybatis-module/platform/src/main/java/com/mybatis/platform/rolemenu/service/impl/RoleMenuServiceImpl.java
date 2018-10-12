package com.mybatis.platform.rolemenu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mybatis.core.orm.common.service.impl.BaseServiceImpl;
import com.mybatis.platform.rolemenu.dao.RoleMenuMapper;
import com.mybatis.platform.rolemenu.entity.RoleMenu;
import com.mybatis.platform.rolemenu.service.RoleMenuService;

import java.util.List;

@Service
public class RoleMenuServiceImpl extends BaseServiceImpl<RoleMenu, String, RoleMenuMapper> implements RoleMenuService{

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Override
    public List<RoleMenu> getRoleMenuList(String roleId) {
        return roleMenuMapper.getRoleMenuList(roleId);
    }
}
