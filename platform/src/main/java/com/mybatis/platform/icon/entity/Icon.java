package com.mybatis.platform.icon.entity;

import com.mybatis.core.orm.annotation.Column;
import com.mybatis.core.orm.annotation.Table;
import com.mybatis.core.orm.entity.IdEntity;

/**
 * @program: mybatis-base
 * @description: 图标管理
 * @author: Mr.Huang
 * @create: 2018-08-22 12:30
 **/
@Table(name = "sys_icon", remark = "图标管理")
public class Icon extends IdEntity {

    /**
     * 图标名称
     */
    @Column(name = "name", comment = "图标名称")
    private String name;

    /**
     * 图标属性
     */
    @Column(name = "attr", comment = "图标属性")
    private String attr;

    /**
     * 是否启用： 1 启用 0 停用
     */
    @Column(name = "enable", comment = "是否启用： 1 启用 0 停用")
    private Integer enable;

    @Column(name = "remark", comment = "图标备注信息", lenght = 500)
    private String remark;

    /**
     * 获取图标名称
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * 设置图标名称
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取图标属性
     *
     * @return
     */
    public String getAttr() {
        return attr;
    }

    /**
     * 设置图标属性
     *
     * @param attr
     */
    public void setAttr(String attr) {
        this.attr = attr;
    }

    /**
     * 获取是否启用
     *
     * @return
     */
    public Integer getEnable() {
        return enable;
    }

    /**
     * 设置是否启用
     *
     * @param enable
     */
    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    /**
     * 获取备注信息
     * @return
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注信息
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
}
