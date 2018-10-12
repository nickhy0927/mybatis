package com.mybatis.deploy.category.entity;

import com.mybatis.cms.SysConstant;
import com.mybatis.core.orm.annotation.Column;
import com.mybatis.core.orm.annotation.Table;
import com.mybatis.core.orm.entity.IdEntity;

@Table(name = "t_d_category", remark = "栏目管理")
public class Category extends IdEntity {

    /**
     * 栏目名称
     */
    @Column(name = "name", comment = "栏目名称 ", length = 128)
    private String name;

    /**
     * 栏目编号
     */
    @Column(name = "code", comment = "栏目编号 ", length = 32)
    private String code;

    /**
     * 父级ID
     */
    @Column(name = "category_id", comment = "父级ID ", length = 32)
    private String categoryId;

    /**
     * 栏目类型
     */
    @Column(name = "type", comment = "栏目类型", length = 10)
    private Integer type;

    /**
     * 栏目类型名称
     */
    private String typeName;

    /**
     * 父级名称
     */
    private String parentName;

    /**
     * 获取：栏目名称
     */
    public String getName() {
        return this.name;
    }

    /**
     * 设置：栏目名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取：栏目编号
     */
    public String getCode() {
        return this.code;
    }

    /**
     * 设置：栏目编号
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取：父级ID
     */
    public String getCategoryId() {
        return this.categoryId;
    }

    /**
     * 设置：父级ID
     */
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * 获取：栏目类型
     */
    public Integer getType() {
        this.typeName = SysConstant.getCategoryName(this.type);
        return this.type;
    }

    /**
     * 设置：栏目类型
     */
    public void setType(Integer type) {
        this.type = type;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}