package com.mybatis.config.database.entity;

import com.mybatis.core.orm.annotation.Column;
import com.mybatis.core.orm.annotation.Table;
import com.mybatis.core.orm.entity.IdEntity;

@Table(name = "t_config_database", remark = "数据库管理")
public class Database extends IdEntity {

	/**
	 * 数据库连接ip
	 */
	@Column(comment = "数据库连接ip")
	private String connectUrl;

	/**
	 * 数据库名称
	 */
	@Column(comment = "数据库名称")
	private String databaseName;

    /**
     * 数据库驱动名称
     */
    @Column(comment = "数据库驱动名称")
	private String driverClassName;

	/**
	 * 数据库用户名
	 */
	@Column(comment = "数据库用户名")
	private String username;

	/**
	 * 数据库密码
	 */
	@Column(comment = "数据库密码")
	private String password;

	/**
	 * 是否启用
	 */
	@Column(comment = "是否启用 1 启用 2停用")
	private Boolean used;

	/**
	 * 获取：数据库连接ip
	 * @return
	 */
	public String getConnectUrl() {
		return connectUrl;
	}

	/**
	 * 设置：数据库连接ip
	 * @param connectUrl
	 */
	public void setConnectUrl(String connectUrl) {
		this.connectUrl = connectUrl;
	}

	/**
	 * 获取：数据库名称
	 * @return
	 */
	public String getDatabaseName() {
		return databaseName;
	}

	/**
	 * 设置：数据库名称
	 * @param databaseName
	 */
	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	/**
	 * 获取：数据库用户名
	 * @return
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * 设置：数据库用户名
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * 获取：数据库密码
	 * @return
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 设置：数据库用户名
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 获取：启用状态
	 * @return
	 */
	public Boolean isUsed() {
		return used;
	}

	/**
	 * 设置：启用状态
	 * @param used
	 */
	public void setUsed(Boolean used) {
		this.used = used;
	}

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }
}
