package com.mybatis.platform.baseparams.entity;

import com.mybatis.core.orm.annotation.Column;
import com.mybatis.core.orm.annotation.Table;
import com.mybatis.core.orm.entity.IdEntity;

@Table(name = "sys_base_params", remark = "")
public class BaseParams extends IdEntity {
	
	/**
	  * 参数编号 
	  */
    @Column(name = "code",comment = "参数编号 ")
	private String code;
	
	/**
	  * 参数名称 
	  */
    @Column(name = "name",comment = "参数名称 ")
	private String name;
	
	/**
	  * 参数值 
	  */
    @Column(name = "val",comment = "参数值 ")
	private String val;
	
	/**
	  * 是否启用 1 启用  0 停用 
	  */
    @Column(name = "enable",comment = "是否启用 1 启用  0 停用 ")
	private Integer enable;
	
	/**
	  * 信息备注
	  */
    @Column(name = "remark",comment = "信息备注")
	private String remark;
	
    /**
     * 获取：参数编号 
     */
    public String getCode() {
       return this.code;
    }
    
    /**
     * 设置：参数编号 
     */
    public void setCode(String code) {
       this.code = code;
    }

    /**
     * 获取：参数名称 
     */
    public String getName() {
       return this.name;
    }
    
    /**
     * 设置：参数名称 
     */
    public void setName(String name) {
       this.name = name;
    }

    /**
     * 获取：参数值 
     */
    public String getVal() {
       return this.val;
    }
    
    /**
     * 设置：参数值 
     */
    public void setVal(String val) {
       this.val = val;
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
}