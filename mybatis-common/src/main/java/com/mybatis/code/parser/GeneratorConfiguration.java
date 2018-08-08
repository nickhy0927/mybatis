/**
 * @Title: GeneratorConfiguration.java  
 * @Package com.iss.code.parser 
 * @author yuanhuangd
 * @version V1.0 
 * @Date: 2018年6月2日 下午3:42:17
 */
package com.mybatis.code.parser;

public class GeneratorConfiguration {

	private String driverClassName;
	private String connectUrl;
	private String username;
	private String password;
	private String projectPath;
	private boolean overwrite;
	private boolean entitywrite;
	private boolean daowrite;
	private boolean servicewrite;
	private boolean controllerwrite;
	private String packages;
	private String entityName;
	private String tableName;
	private String sqlMapperPath;
	private boolean list;
	private boolean create;
	private boolean edit;
	private String baseDir;
	private String target;

	public String getBaseDir() {
		return baseDir;
	}

	public void setBaseDir(String baseDir) {
		this.baseDir = baseDir;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public boolean isList() {
		return list;
	}

	public void setList(boolean list) {
		this.list = list;
	}

	public boolean isCreate() {
		return create;
	}

	public void setCreate(boolean create) {
		this.create = create;
	}

	public boolean isEdit() {
		return edit;
	}

	public void setEdit(boolean edit) {
		this.edit = edit;
	}

	public String getDriverClassName() {
		return driverClassName;
	}

	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}

	public String getConnectUrl() {
		return connectUrl;
	}

	public void setConnectUrl(String connectUrl) {
		this.connectUrl = connectUrl;
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

	public String getProjectPath() {
		return projectPath;
	}

	public void setProjectPath(String projectPath) {
		this.projectPath = projectPath;
	}

	public boolean isOverwrite() {
		return overwrite;
	}

	public void setOverwrite(boolean overwrite) {
		this.overwrite = overwrite;
	}

	public boolean isEntitywrite() {
		return entitywrite;
	}

	public void setEntitywrite(boolean entitywrite) {
		this.entitywrite = entitywrite;
	}

	public boolean isDaowrite() {
		return daowrite;
	}

	public void setDaowrite(boolean daowrite) {
		this.daowrite = daowrite;
	}

	public boolean isServicewrite() {
		return servicewrite;
	}

	public void setServicewrite(boolean servicewrite) {
		this.servicewrite = servicewrite;
	}

	public boolean isControllerwrite() {
		return controllerwrite;
	}

	public void setControllerwrite(boolean controllerwrite) {
		this.controllerwrite = controllerwrite;
	}

	public String getPackages() {
		return packages;
	}

	public void setPackages(String packages) {
		this.packages = packages;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getSqlMapperPath() {
		return sqlMapperPath;
	}

	public void setSqlMapperPath(String sqlMapperPath) {
		this.sqlMapperPath = sqlMapperPath;
	}

	@Override
	public String toString() {
		return "GeneratorConfiguration [projectPath=" + projectPath + ", overwrite=" + overwrite + ", entitywrite=" + entitywrite + ", daowrite=" + daowrite
				+ ", servicewrite=" + servicewrite + ", controllerwrite=" + controllerwrite + ", packages=" + packages + ", entityName=" + entityName
				+ ", tableName=" + tableName + ", sqlMapperPath=" + sqlMapperPath + "]";
	}

}
