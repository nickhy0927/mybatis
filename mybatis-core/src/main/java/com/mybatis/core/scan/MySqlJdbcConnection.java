package com.mybatis.core.scan;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import com.mybatis.common.utils.Underline2Camel;
import com.mybatis.core.orm.annotation.Column;
import com.mybatis.core.orm.annotation.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MySqlJdbcConnection {

	private Logger logger = LoggerFactory.getLogger(MySqlJdbcConnection.class);
	private static Connection connection = null;

	private static PreparedStatement preparedStatement = null;

	private static ResultSet resultSet = null;
	
	/**
	 * 数据库名称
	 */
	private String dbName;

	public MySqlJdbcConnection(String driverClassName, String connectUrl, String username, String password,String dbName) {
		try {
			this.dbName = dbName;
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(connectUrl, username, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
	
	/**
	 * 初始化数据库表
	 * 
	 * @throws SQLException
	 */
	public void initTable(Set<Class<?>> pkgs) {
		logger.debug("开始初始化数据库...");
		try {
			for (Class<?> clazz : pkgs) {
				Class<?> superclass = clazz.getSuperclass();
				Table table = clazz.getDeclaredAnnotation(Table.class);
				String sql = "select table_name as tableName from information_schema.tables where table_schema='" + dbName +"' and table_name= ? ";
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, table.name());
				resultSet = preparedStatement.executeQuery();
				if (resultSet.next()) {
					Field[] fields = superclass.getDeclaredFields();
					for (Field field : fields) {
						// 执行修改表结构操作
						editTable(table, field);
					}
					for (Field field : clazz.getDeclaredFields()) {
						// 执行修改表结构操作
						editTable(table, field);
					}
				} else {
					// 执行创建表sql语句
					createTable(table, clazz);
				}
			}
			
			System.out.println(connection);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		logger.debug("数据库初始化完成...");
	}
	
	/**
	 * 创建数据库表
	 * @param table
	 * @param clazz
	 * @throws SQLException 
	 */
	public void createTable(Table table, Class<?> clazz) throws SQLException {
		StringBuilder builder = new StringBuilder();
		Class<?> superclass = clazz.getSuperclass();
		builder.append("create table ").append(table.name()).append("(");
		Field[] fields = superclass.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Column column = fields[i].getAnnotation(Column.class);
			String dataType = GenericType.getDataType(fields[i], column);
			String columnName = StringUtils.isNotEmpty(column.name()) ? column.name() : Underline2Camel.camel2Underline(fields[i].getName()).toLowerCase();
			if (column.isKey()) {
				builder.append(columnName).append(" ");
				builder.append(dataType).append(" primary key");
			} else {
				builder.append(columnName).append(" ");
				builder.append(dataType);
				if (column.status() != -1) {
					builder.append(" default ").append(column.status());
				}
			}
			builder.append(" comment '").append(column.comment()).append("'").append(",");
		}
		fields = clazz.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Column column = fields[i].getAnnotation(Column.class);
			if (i != fields.length - 1) {
				String dataType = GenericType.getDataType(fields[i], column);
				String columnName = StringUtils.isNotEmpty(column.name()) ? column.name() : Underline2Camel.camel2Underline(fields[i].getName()).toLowerCase();
				builder.append(columnName).append(" ");
				builder.append(dataType).append(" comment '");
				builder.append(column.comment()).append(" '").append(",");;
			} else {
				String dataType = GenericType.getDataType(fields[i], column);
				String columnName = StringUtils.isNotEmpty(column.name()) ? column.name() : Underline2Camel.camel2Underline(fields[i].getName()).toLowerCase();
				builder.append(columnName).append(" ");
				builder.append(dataType).append(" comment '");
				builder.append(column.comment()).append("'");
			}
		}
		builder.append(")").append(" comment = ").append("'").append(table.remark()).append("'");
		logger.debug(builder.toString());
		preparedStatement = connection.prepareStatement(builder.toString());
		preparedStatement.execute();
	}

	/**
	 * 修改数据库表
	 * @param clazz 
	 * @param table 
	 * @throws SQLException 
	 */
	public void editTable(Table table, Field field) throws SQLException {
		Column column = field.getDeclaredAnnotation(Column.class);
		if (column != null) {
			String queryColumnSql = "select  column_name as columnName, data_type dataType, is_nullable isNullAble, column_default columnDefault, column_comment as columnComment from information_schema.columns where table_name = ? AND table_schema = ? and column_name = ?";
			preparedStatement = connection.prepareStatement(queryColumnSql);
			preparedStatement.setString(1, table.name());
			String columnName = StringUtils.isNotEmpty(column.name()) ? column.name() : Underline2Camel.camel2Underline(field.getName()).toLowerCase();
			preparedStatement.setString(2, dbName);
			preparedStatement.setString(3, columnName);
			resultSet = preparedStatement.executeQuery();
			TableColumn tableColumn = TableColumn.getDefaultTableColumn(resultSet);
			if (tableColumn == null) {
				String alterSql = "alter table " + table.name() + " add column " + columnName +" " + GenericType.getDataType(field, column);
				logger.debug(alterSql);
				PreparedStatement prepareStatement = connection.prepareStatement(alterSql);
				prepareStatement.execute();
			}
		}
	}
}
