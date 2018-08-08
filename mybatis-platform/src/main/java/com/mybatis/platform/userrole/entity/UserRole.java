package com.mybatis.platform.userrole.entity;

import com.mybatis.core.orm.annotation.Column;
import com.mybatis.core.orm.annotation.Table;
import com.mybatis.core.orm.entity.IdEntity;

@Table(name = "sys_user_role", remark = "")
public class UserRole extends IdEntity {
	
	/**
	  * 用户ID 
	  */
    @Column(name = "user_id",comment = "用户ID ")
	private String userId;
	
	/**
	  * 角色ID
	  */
    @Column(name = "role_id",comment = "角色ID")
	private String roleId;
	

    /**
     * 获取：用户ID 
     */
    public String getUserId() {
       return this.userId;
    }
    
    /**
     * 设置：用户ID 
     */
    public void setUserId(String userId) {
       this.userId = userId;
    }

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
}