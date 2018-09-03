package com.mybatis.platform.attachment.entity;

import java.util.Date;

import com.mybatis.core.orm.annotation.Column;
import com.mybatis.core.orm.annotation.Table;
import com.mybatis.core.orm.entity.IdEntity;

@Table(name = "sys_attach", remark = "")
public class Attachment extends IdEntity {
	
	
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
	  * 附件的名称 
	  */
    @Column(name = "name", comment = "附件的名称 ", length = 128)
	private String name;
	
	/**
	  * 附件上传的路径 
	  */
    @Column(name = "path", comment = "附件上传的路径 ", length = 128)
	private String path;
	
	/**
	  * 附件的类型 
	  */
    @Column(name = "file_type", comment = "附件的类型 ", length = 200)
	private String fileType;
	
	/**
	  * 附件的大小 
	  */
    @Column(name = "file_size", comment = "附件的大小 ", length = 128)
	private String fileSize;
	
	/**
	  * 附件原始名称 
	  */
    @Column(name = "file_name", comment = "附件原始名称 ", length = 128)
	private String fileName;
	
	/**
	  * 附件原始名称
	  */
    @Column(name = "suffix", comment = "附件原始名称", length = 8)
	private String suffix;
	

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
     * 获取：附件的名称 
     */
    public String getName() {
       return this.name;
    }
    
    /**
     * 设置：附件的名称 
     */
    public void setName(String name) {
       this.name = name;
    }

    /**
     * 获取：附件上传的路径 
     */
    public String getPath() {
       return this.path;
    }
    
    /**
     * 设置：附件上传的路径 
     */
    public void setPath(String path) {
       this.path = path;
    }

    /**
     * 获取：附件的类型 
     */
    public String getFileType() {
       return this.fileType;
    }
    
    /**
     * 设置：附件的类型 
     */
    public void setFileType(String fileType) {
       this.fileType = fileType;
    }

    /**
     * 获取：附件的大小 
     */
    public String getFileSize() {
       return this.fileSize;
    }
    
    /**
     * 设置：附件的大小 
     */
    public void setFileSize(String fileSize) {
       this.fileSize = fileSize;
    }

    /**
     * 获取：附件原始名称 
     */
    public String getFileName() {
       return this.fileName;
    }
    
    /**
     * 设置：附件原始名称 
     */
    public void setFileName(String fileName) {
       this.fileName = fileName;
    }

    /**
     * 获取：附件原始名称
     */
    public String getSuffix() {
       return this.suffix;
    }
    
    /**
     * 设置：附件原始名称
     */
    public void setSuffix(String suffix) {
       this.suffix = suffix;
    }
}