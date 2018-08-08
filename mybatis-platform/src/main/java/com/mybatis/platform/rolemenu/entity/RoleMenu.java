package com.mybatis.platform.rolemenu.entity;

import com.mybatis.core.orm.annotation.Column;
import com.mybatis.core.orm.annotation.Table;
import com.mybatis.core.orm.entity.IdEntity;

@Table(name = "sys_role_menu", remark = "")
public class RoleMenu extends IdEntity {
	
	/**
	  * 角色ID 
	  */
    @Column(name = "role_id",comment = "角色ID ")
	private String roleId;
	
	/**
	  * 菜单ID
	  */
    @Column(name = "menu_id",comment = "菜单ID")
	private String menuId;
	
    /**
     * 获取：角色ID 
     */
    public String getRoleId() {
       return this.roleId;
    }
    
    /**
     * 设置：角色ID 
     */
    public void setRoleId(String roleId) {
       this.roleId = roleId;
    }

    /**
     * 获取：菜单ID
     */
    public String getMenuId() {
       return this.menuId;
    }
    
    /**
     * 设置：菜单ID
     */
    public void setMenuId(String menuId) {
       this.menuId = menuId;
    }
}