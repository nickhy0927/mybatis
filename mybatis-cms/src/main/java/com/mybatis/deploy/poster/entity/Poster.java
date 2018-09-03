package com.mybatis.deploy.poster.entity;

import com.mybatis.core.orm.annotation.Column;
import com.mybatis.core.orm.annotation.Table;
import com.mybatis.core.orm.entity.IdEntity;

@Table(name = "t_d_poster", remark = "")
public class Poster extends IdEntity {
	
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