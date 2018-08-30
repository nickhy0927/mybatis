package com.mybatis.deploy.news.entity;

import java.util.Date;

import com.mybatis.core.orm.annotation.Column;
import com.mybatis.core.orm.annotation.Table;
import com.mybatis.core.orm.entity.IdEntity;

@Table(name = "t_d_news", remark = "")
public class News extends IdEntity {
	
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