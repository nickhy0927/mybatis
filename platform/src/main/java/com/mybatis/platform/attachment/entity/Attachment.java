package com.mybatis.platform.attachment.entity;

import com.mybatis.core.orm.annotation.Column;
import com.mybatis.core.orm.annotation.Table;
import com.mybatis.core.orm.entity.IdEntity;

/**
 * 附件的大小
 * 
 * @author HUANGYUAN
 *
 */
@Table(name = "sys_attach")
public class Attachment extends IdEntity {

	/**
	 * 附件的名称
	 */
	@Column(name = "name", comment = "附件的名称", length = 128)
	private String name;

	/**
	 * 附件上传的路径
	 */
	@Column(name = "path", comment = "附件上传的路径", length = 128)
	private String path;
	/**
	 * 附件的类型
	 */
	@Column(name = "file_type", comment = "附件的类型")
	private String fileType;

	/**
	 * 附件的大小
	 */
	@Column(name = "file_size", comment = "附件的大小", length = 128)
	private String fileSize;
	
	/**
	 * 附件原始名称
	 */
	@Column(name = "file_name", comment = "附件原始名称", length = 128)
	private String fileName;
	
	/**
	 * 文件后缀名
	 */
	@Column(name = "suffix", comment = "附件原始名称", length = 8)
	private String suffix;

	public Attachment() {

	}

	public Attachment(String name, String path, String type, String fileSize, String suffix) {
		super();
		this.name = name;
		this.path = path;
		this.fileType = type;
		this.fileSize = fileSize;
		this.suffix = suffix;
	}

	public String getFileName() {
		return fileName;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getFileSize() {
		return fileSize;
	}
	
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	
	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

}
