package com.mybatis.platform.role.entity;

import com.mybatis.core.orm.annotation.Column;
import com.mybatis.core.orm.annotation.Table;
import com.mybatis.core.orm.entity.IdEntity;

@Table(name = "sys_role", remark = "")
public class Role extends IdEntity {
	
	/**
	  * 角色编号 
	  */
    @Column(name = "code",comment = "角色编号 ")
	private String code;
	
	/**
	  * 角色名称 
	  */
    @Column(name = "name",comment = "角色名称 ")
	private String name;
	
	/**
	  * 上级角色 
	  */
    @Column(name = "role_id",comment = "上级角色 ")
	private String roleId;
	
	/**
	  * 信息备注 
	  */
    @Column(name = "remark",comment = "信息备注 ")
	private String remark;
	
	/**
	  * 是否冻结 1 冻结 0 未冻结
	  */
    @Column(name = "frozen",comment = "是否冻结 1 冻结 0 未冻结")
	private Integer frozen;
	
    /**
     * 获取：角色编号 
     */
    public String getCode() {
       return this.code;
    }
    
    /**
     * 设置：角色编号 
     */
    public void setCode(String code) {
       this.code = code;
    }

    /**
     * 获取：角色名称 
     */
    public String getName() {
       return this.name;
    }
    
    /**
     * 设置：角色名称 
     */
    public void setName(String name) {
       this.name = name;
    }

    /**
     * 获取：上级角色 
     */
    public String getRoleId() {
       return this.roleId;
    }
    
    /**
     * 设置：上级角色 
     */
    public void setRoleId(String roleId) {
       this.roleId = roleId;
    }

    /**
     * 获取：信息备注 
     */
    public String getRemark() {
       return this.remark;
    }
    
    /**
     * 设置：信息备注 
     */
    public void setRemark(String remark) {
       this.remark = remark;
    }

    /**
     * 获取：是否冻结 1 冻结 0 未冻结
     */
    public Integer getFrozen() {
       return this.frozen;
    }
    
    /**
     * 设置：是否冻结 1 冻结 0 未冻结
     */
    public void setFrozen(Integer frozen) {
       this.frozen = frozen;
    }
}