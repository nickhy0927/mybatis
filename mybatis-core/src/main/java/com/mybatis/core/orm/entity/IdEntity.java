package com.mybatis.core.orm.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mybatis.common.utils.CustomDateOne;
import com.mybatis.core.orm.annotation.Column;
import com.mybatis.core.orm.constant.SysConstant;

import java.util.Date;

/**
 * 基础类
 *
 * @author yuanhuangd
 */
public class IdEntity {

    /**
     * 主键ID
     */
    @Column(name = "id" ,isKey = true, comment = "主键ID")
    protected String id;

    /**
     * 新增时间
     */
    @Column(name = "create_time",createTime = true, comment = "新增时间")
    @JsonSerialize(using = CustomDateOne.class)
    protected Date createTime;

    /**
     * 修改时间
     */
    @Column(comment = "修改时间")
    @JsonSerialize(using = CustomDateOne.class)
    protected Date updateTime;

    /**
     * 有效状态 1 ：有效 ，0 ： 无效
     */
    @Column(status = SysConstant.DataStatus.VALID, comment = "有效状态")
    protected Integer status;

    protected String statusName;

    public String getStatusName() {
        return SysConstant.DataStatus.getName(this.status);
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
