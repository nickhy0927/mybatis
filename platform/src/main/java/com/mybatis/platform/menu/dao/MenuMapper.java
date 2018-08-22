package com.mybatis.platform.menu.dao;

import com.mybatis.platform.menu.entity.Menu;
import com.mybatis.core.orm.common.mapper.BaseMapper;

import java.util.List;

public interface MenuMapper extends BaseMapper<Menu,String> {

    /**
     * 查询父级菜单
     * @return
     */
    List<Menu> queryMenuByParentIsNull();

    /**
     * 根据父节点查询菜单
     * @return
     */
    List<Menu> queryMenuByMenuId(Menu menu);
}