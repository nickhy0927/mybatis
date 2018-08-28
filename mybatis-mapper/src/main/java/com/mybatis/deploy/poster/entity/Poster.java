package com.mybatis.deploy.poster.entity;

import java.util.Date;

import com.mybatis.core.orm.annotation.Column;
import com.mybatis.core.orm.annotation.Table;
import com.mybatis.core.orm.entity.IdEntity;

@Table(name = "t_d_poster", remark = "")
public class Poster extends IdEntity {
	
	
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
	  * 附件ID 
	  */
    @Column(name = "attachment_id", comment = "附件ID ", length = 32)
	private String attachmentId;
	
	/**
	  * 排序 
	  */
    @Column(name = "sort", comment = "排序 ", length = 10)
	private Integer sort;
	
	/**
	  * 图片备注 
	  */
    @Column(name = "remaker", comment = "图片备注 ", length = 500)
	private String remaker;
	
	/**
	  * 访问连接
	  */
    @Column(name = "url", comment = "访问连接", length = 256)
	private String url;
	

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
     * 获取：附件ID 
     */
    public String getAttachmentId() {
       return this.attachmentId;
    }
    
    /**
     * 设置：附件ID 
     */
    public void setAttachmentId(String attachmentId) {
       this.attachmentId = attachmentId;
    }

    /**
     * 获取：排序 
     */
    public Integer getSort() {
       return this.sort;
    }
    
    /**
     * 设置：排序 
     */
    public void setSort(Integer sort) {
       this.sort = sort;
    }

    /**
     * 获取：图片备注 
     */
    public String getRemaker() {
       return this.remaker;
    }
    
    /**
     * 设置：图片备注 
     */
    public void setRemaker(String remaker) {
       this.remaker = remaker;
    }

    /**
     * 获取：访问连接
     */
    public String getUrl() {
       return this.url;
    }
    
    /**
     * 设置：访问连接
     */
    public void setUrl(String url) {
       this.url = url;
    }
}