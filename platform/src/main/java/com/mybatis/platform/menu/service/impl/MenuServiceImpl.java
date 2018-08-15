package com.mybatis.platform.menu.service.impl;

import org.springframework.stereotype.Service;

import com.mybatis.core.orm.common.service.impl.BaseServiceImpl;
import com.mybatis.platform.menu.dao.MenuMapper;
import com.mybatis.platform.menu.entity.Menu;
import com.mybatis.platform.menu.service.MenuService;

@Service
public class MenuServiceImpl extends BaseServiceImpl<Menu, String, MenuMapper> implements MenuService{

}
