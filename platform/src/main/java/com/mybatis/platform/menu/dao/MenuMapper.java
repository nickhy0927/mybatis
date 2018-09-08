package com.mybatis.platform.menu.dao;

import com.mybatis.platform.menu.entity.Menu;
import com.mybatis.core.orm.common.mapper.BaseMapper;
import com.mybatis.platform.role.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
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

    /**
     * 根据角色查询菜单信息
     * @param roleList
     * @return
     */
    List<Menu> queryMenuByRoleList(List<Role> roleList);
}