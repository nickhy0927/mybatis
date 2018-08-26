package com.mybatis.config.optlog.entity;

import com.mybatis.core.orm.annotation.Column;
import com.mybatis.core.orm.annotation.Table;
import com.mybatis.core.orm.entity.IdEntity;

@Table(name = "sys_opt_log", remark = "操作日志")
public class OptLog extends IdEntity {
	
	/**
	  * 操作人名字 
	  */
    @Column(name = "username",comment = "操作人名字 ")
	private String username;
	
	/**
	  * 操作人ID 
	  */
    @Column(name = "user_id",comment = "操作人ID ")
	private String userId;
	
	/**
	  * 操作信息 
	  */
    @Column(name = "message",comment = "操作信息 ")
	private String message;
	
	/**
	  * 操作信息 
	  */
    @Column(name = "method",comment = "操作信息 ")
	private String method;
	
	/**
	  * 操作类名称 
	  */
    @Column(name = "clazz",comment = "操作类名称 ")
	private String clazz;
	
	/**
	  * 操作类名称
	  */
    @Column(name = "opt_type",comment = "操作类名称")
	private Integer optType;
	

    /**
     * 获取：操作人名字 
     */
    public String getUsername() {
       return this.username;
    }
    
    /**
     * 设置：操作人名字 
     */
    public void setUsername(String username) {
       this.username = username;
    }

    /**
     * 获取：操作人ID 
     */
    public String getUserId() {
       return this.userId;
    }
    
    /**
     * 设置：操作人ID 
     */
    public void setUserId(String userId) {
       this.userId = userId;
    }

    /**
     * 获取：操作信息 
     */
    public String getMessage() {
       return this.message;
    }
    
    /**
     * 设置：操作信息 
     */
    public void setMessage(String message) {
       this.message = message;
    }

    /**
     * 获取：操作信息 
     */
    public String getMethod() {
       return this.method;
    }
    
    /**
     * 设置：操作信息 
     */
    public void setMethod(String method) {
       this.method = method;
    }

    /**
     * 获取：操作类名称 
     */
    public String getClazz() {
       return this.clazz;
    }
    
    /**
     * 设置：操作类名称 
     */
    public void setClazz(String clazz) {
       this.clazz = clazz;
    }

    /**
     * 获取：操作类名称
     */
    public Integer getOptType() {
       return this.optType;
    }
    
    /**
     * 设置：操作类名称
     */
    public void setOptType(Integer optType) {
       this.optType = optType;
    }
    
}