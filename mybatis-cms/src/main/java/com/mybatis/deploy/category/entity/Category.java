package com.mybatis.deploy.category.entity;

import java.util.Date;

import com.mybatis.core.orm.annotation.Column;
import com.mybatis.core.orm.annotation.Table;
import com.mybatis.core.orm.entity.IdEntity;

@Table(name = "t_d_category", remark = "")
public class Category extends IdEntity {
	
	
	/**
	  * 主键ID
	  */
    @Column(name = "id", comment = "主键ID", length = 200)
	private String id;
	
	/**
	  * 新增时间
	  */
    @Column(name = "create_time", comment = "新增时间", length = 19)
	private Date createTime;
	
	/**
	  * 修改时间
	  */
    @Column(name = "update_time", comment = "修改时间", length = 19)
	private Date updateTime;
	
	/**
	  * 有效状态
	  */
    @Column(name = "status", comment = "有效状态", length = 10)
	private Integer status;
	
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
     * 获取：主键ID
     */
    public String getId() {
       return this.id;
    }
    
    /**
     * 设置：主键ID
     */
    public void setId(String id) {
       this.id = id;
    }

    /**
     * 获取：新增时间
     */
    public Date getCreateTime() {
       return this.createTime;
    }
    
    /**
     * 设置：新增时间
     */
    public void setCreateTime(Date createTime) {
       this.createTime = createTime;
    }

    /**
     * 获取：修改时间
     */
    public Date getUpdateTime() {
       return this.updateTime;
    }
    
    /**
     * 设置：修改时间
     */
    public void setUpdateTime(Date updateTime) {
       this.updateTime = updateTime;
    }

    /**
     * 获取：有效状态
     */
    public Integer getStatus() {
       return this.status;
    }
    
    /**
     * 设置：有效状态
     */
    public void setStatus(Integer status) {
       this.status = status;
    }

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
       return this.type;
    }
    
    /**
     * 设置：栏目类型
     */
    public void setType(Integer type) {
       this.type = type;
    }
}