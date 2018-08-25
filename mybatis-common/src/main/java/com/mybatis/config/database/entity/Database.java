package com.mybatis.config.database.entity;

import com.mybatis.core.orm.annotation.Column;
import com.mybatis.core.orm.annotation.Table;
import com.mybatis.core.orm.entity.IdEntity;

@Table(name = "t_config_database", remark = "数据库管理")
public class Database extends IdEntity {

	@Column(comment = "数据库连接ip")
	private String ip;

	@Column(comment = "数据库名称")
	private String databaseName;

	@Column(comment = "数据库驱动名称")
	private String driverClassName;

	@Column(comment = "数据库用户名")
	private String username;

	@Column(comment = "数据库密码")
	private String password;

	@Column(comment = "是否启用 1 启用 2停用")
	private Boolean used;

	@Column(comment = "mysqldump命令目录")
	private String mysqldumpPath;

	@Column(comment = "连接数据库参数")
	private String params;

	@Column(comment = "数据库类型1 mysql 2 oracle")
	private Integer databaseType;

	@Column(comment = "连接数据库端口")
	private Integer port;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getDatabaseName() {
		return databaseName;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	public String getDriverClassName() {
		return driverClassName;
	}

	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getUsed() {
		return used;
	}

	public void setUsed(Boolean used) {
		this.used = used;
	}

	public String getMysqldumpPath() {
		return mysqldumpPath;
	}

	public void setMysqldumpPath(String mysqldumpPath) {
		this.mysqldumpPath = mysqldumpPath;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public Integer getDatabaseType() {
		return databaseType;
	}

	public void setDatabaseType(Integer databaseType) {
		this.databaseType = databaseType;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}
}
