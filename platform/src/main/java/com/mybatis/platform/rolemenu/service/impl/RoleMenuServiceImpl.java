package com.mybatis.platform.rolemenu.service.impl;

import org.springframework.stereotype.Service;

import com.mybatis.core.orm.common.service.impl.BaseServiceImpl;
import com.mybatis.platform.rolemenu.dao.RoleMenuMapper;
import com.mybatis.platform.rolemenu.entity.RoleMenu;
import com.mybatis.platform.rolemenu.service.RoleMenuService;

@Service
public class RoleMenuServiceImpl extends BaseServiceImpl<RoleMenu, String, RoleMenuMapper> implements RoleMenuService{

}
