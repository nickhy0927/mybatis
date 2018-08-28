package com.mybatis.deploy.news.entity;

import java.util.Date;

import com.mybatis.core.orm.annotation.Column;
import com.mybatis.core.orm.annotation.Table;
import com.mybatis.core.orm.entity.IdEntity;

@Table(name = "t_d_news", remark = "")
public class News extends IdEntity {
	
	
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
	  * 新闻标题 
	  */
    @Column(name = "title", comment = "新闻标题 ", length = 256)
	private String title;
	
	/**
	  * 新闻内容 
	  */
    @Column(name = "content", comment = "新闻内容 ", length = 5000)
	private String content;
	
	/**
	  * 栏目ID 
	  */
    @Column(name = "category_id", comment = "栏目ID ", length = 32)
	private String categoryId;
	
	/**
	  * 新闻创建人ID
	  */
    @Column(name = "user_id", comment = "新闻创建人ID", length = 32)
	private String userId;
	

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
     * 获取：新闻标题 
     */
    public String getTitle() {
       return this.title;
    }
    
    /**
     * 设置：新闻标题 
     */
    public void setTitle(String title) {
       this.title = title;
    }

    /**
     * 获取：新闻内容 
     */
    public String getContent() {
       return this.content;
    }
    
    /**
     * 设置：新闻内容 
     */
    public void setContent(String content) {
       this.content = content;
    }

    /**
     * 获取：栏目ID 
     */
    public String getCategoryId() {
       return this.categoryId;
    }
    
    /**
     * 设置：栏目ID 
     */
    public void setCategoryId(String categoryId) {
       this.categoryId = categoryId;
    }

    /**
     * 获取：新闻创建人ID
     */
    public String getUserId() {
       return this.userId;
    }
    
    /**
     * 设置：新闻创建人ID
     */
    public void setUserId(String userId) {
       this.userId = userId;
    }
}