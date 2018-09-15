package com.iss.module.platform.user.pojo;

import java.util.Date;

public class User {
	
	private String id;
	/**
     * 真实姓名
     */
    private String realName;

    /**
     * 登录账号
     */
    private String loginName;

    /**
     * 是否锁定 1 锁定 0 未锁定
     */
    private Integer locked;

    /**
     * 是否启用 1 启用 0 停用
     */
    private Integer enable;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 电话号码
     */
    private String mobile;

    /**
     * 信息备注
     */
    private String remark;

    /**
     * 用户职位
     */
    private String position;

    /**
     * 最后一次登录时间
     */
    private Date lastLoginTime;

    /**
     * 登录次数
     */
    private String loginCount;

    /**
     * 登录IP
     */
    private String lastLoginIp;

    public String getId() {
		return id;
	}
    
    public void setId(String id) {
		this.id = id;
	}
    
    public String getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(String loginCount) {
        this.loginCount = loginCount;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    /**
     * 获取：真实姓名
     */
    public String getRealName() {
        return this.realName;
    }

    /**
     * 设置：真实姓名
     */
    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     * 获取：登录账号
     */
    public String getLoginName() {
        return this.loginName;
    }

    /**
     * 设置：登录账号
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    /**
     * 获取：是否锁定 1 锁定 0 未锁定
     */
    public Integer getLocked() {
        return this.locked;
    }

    /**
     * 设置：是否锁定 1 锁定 0 未锁定
     */
    public void setLocked(Integer locked) {
        this.locked = locked;
    }

    /**
     * 获取：是否启用 1 启用 0 停用
     */
    public Integer getEnable() {
        return this.enable;
    }

    /**
     * 设置：是否启用 1 启用 0 停用
     */
    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    /**
     * 获取：电子邮箱
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * 设置：电子邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取：电话号码
     */
    public String getMobile() {
        return this.mobile;
    }

    /**
     * 设置：电话号码
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
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
     * 获取：用户职位
     */
    public String getPosition() {
        return this.position;
    }

    /**
     * 设置：用户职位
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * 获取：最后一次登录时间
     */
    public Date getLastLoginTime() {
        return this.lastLoginTime;
    }

    /**
     * 设置：最后一次登录时间
     */
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

}
