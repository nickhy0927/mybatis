package com.mybatis.core.scan;

import java.util.Set;

public class ScanPackage {

	/**
	 * 需要扫描的包路径，可以是多个，用,分割
	 */
	private String basePackage;

	/**
	 * 数据库连接地址
	 */
	private String connectUrl;

	/**
	 * 数据库驱动名称
	 */
	private String driverClassName;

	/**
	 * 数据库用户名
	 */
	private String username;

	/**
	 * 数据库密码
	 */
	private String password;
	
	/**
	 * 数据库名称
	 */
	private String dbName;

	/**
	 * 获取：需要扫描的包路径，可以是多个，用,分割
	 * 
	 * @return
	 */
	public String getBasePackage() {
		return basePackage;
	}

	/**
	 * 设置：数据库连接地址
	 * 
	 * @return
	 */
	public String getConnectUrl() {
		return connectUrl;
	}

	/**
	 * 获取：数据库驱动名称
	 * 
	 * @return
	 */
	public String getDriverClassName() {
		return driverClassName;
	}

	/**
	 * 数据库密码
	 * 
	 * @return
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 获取：数据库用户名
	 * 
	 * @return
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * 设置：需要扫描的包路径，可以是多个，用,分割
	 * 
	 * @param basePackage
	 *            需要扫描的包路径，可以是多个，用,分割
	 */
	public void setBasePackage(String basePackage) {
		this.basePackage = basePackage;
	}

	/**
	 * 获取：数据库连接地址
	 * 
	 * @param connectUrl
	 */
	public void setConnectUrl(String connectUrl) {
		this.connectUrl = connectUrl;
	}

	/**
	 * 设置：数据库驱动名称
	 * 
	 * @param driverClassName
	 *            数据库驱动名称
	 */
	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}

	/**
	 * 数据库密码
	 * 
	 * @param password
	 *            数据库密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 设置：数据库用户名
	 * 
	 * @param username
	 *            数据库用户名
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * 获取：数据库名称
	 * @return
	 */
	public String getDbName() {
		return dbName;
	}
	
	/**
	 * 设置：数据库名称
	 * @param dbName
	 */
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
	
	public void init() {
		try {
			Set<Class<?>> pkgs = ClassPathCandidateComponentScanner.getClzFromPkg(basePackage);
			MySqlJdbcConnection mySqlJdbcConnection = new MySqlJdbcConnection(driverClassName, connectUrl, username, password, dbName);
			// 生成创建表的sql语句
			mySqlJdbcConnection.initTable(pkgs);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
