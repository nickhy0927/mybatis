package com.mybatis.platform.basedata.entity;

import com.mybatis.core.orm.annotation.Column;
import com.mybatis.core.orm.annotation.Table;
import com.mybatis.core.orm.entity.IdEntity;

@Table(name = "sys_base_data", remark = "")
public class BaseData extends IdEntity {
	
	/**
	  * 字典编号 
	  */
    @Column(name = "code",comment = "字典编号 ")
	private String code;
	
	/**
	  * 字典名称 
	  */
    @Column(name = "name",comment = "字典名称 ")
	private String name;
	
	/**
	  * 字典值 
	  */
    @Column(name = "val",comment = "字典值 ")
	private String val;
	
	/**
	  * 信息备注 
	  */
    @Column(name = "remark",comment = "信息备注 ")
	private String remark;
	
	/**
	  * 是否启用 1 启用  0 停用 
	  */
    @Column(name = "enable",comment = "是否启用 1 启用  0 停用 ")
	private Integer enable;
	
	/**
	  * 系统参数ID
	  */
    @Column(name = "sys_params_id",comment = "系统参数ID")
	private String sysParamsId;

    /**
     * 获取：字典编号 
     */
    public String getCode() {
       return this.code;
    }
    
    /**
     * 设置：字典编号 
     */
    public void setCode(String code) {
       this.code = code;
    }

    /**
     * 获取：字典名称 
     */
    public String getName() {
       return this.name;
    }
    
    /**
     * 设置：字典名称 
     */
    public void setName(String name) {
       this.name = name;
    }

    /**
     * 获取：字典值 
     */
    public String getVal() {
       return this.val;
    }
    
    /**
     * 设置：字典值 
     */
    public void setVal(String val) {
       this.val = val;
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
     * 获取：是否启用 1 启用  0 停用 
     */
    public Integer getEnable() {
       return this.enable;
    }
    
    /**
     * 设置：是否启用 1 启用  0 停用 
     */
    public void setEnable(Integer enable) {
       this.enable = enable;
    }

    /**
     * 获取：系统参数ID
     */
    public String getSysParamsId() {
       return this.sysParamsId;
    }
    
    /**
     * 设置：系统参数ID
     */
    public void setSysParamsId(String sysParamsId) {
       this.sysParamsId = sysParamsId;
    }
}