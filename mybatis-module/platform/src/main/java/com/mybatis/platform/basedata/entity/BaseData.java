package com.mybatis.platform.basedata.entity;

import com.mybatis.core.orm.annotation.Column;
import com.mybatis.core.orm.annotation.Table;
import com.mybatis.core.orm.entity.IdEntity;

@Table(name = "sys_base_data", remark = "基础数据")
public class BaseData extends IdEntity {

    /**
     * 字典编号
     */
    @Column(name = "code", comment = "字典编号 ")
    private String code;

    /**
     * 字典名称
     */
    @Column(name = "name", comment = "字典名称 ")
    private String name;

    /**
     * 字典值
     */
    @Column(name = "val", comment = "字典值 ")
    private String val;

    /**
     * 信息备注
     */
    @Column(name = "remark", comment = "信息备注 ")
    private String remark;

    /**
     * 是否启用 1 启用  0 停用
     */
    @Column(name = "enable", comment = "是否启用 1 启用  0 停用 ")
    private Integer enable;

    /**
     * 系统参数ID
     */
    @Column(name = "base_data_id", comment = "基础数据上级目录")
    private String baseDataId;

    @Column(name = "sort", comment = "排序")
    private Integer sort;

    /**
     * 上级节点名称
     */
    private String parentName;

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

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
     * 获取：上级基础数据
     *
     * @return
     */
    public String getBaseDataId() {
        return baseDataId;
    }

    /**
     * 设置：上级基础数据
     *
     * @param baseDataId
     */
    public void setBaseDataId(String baseDataId) {
        this.baseDataId = baseDataId;
    }
}