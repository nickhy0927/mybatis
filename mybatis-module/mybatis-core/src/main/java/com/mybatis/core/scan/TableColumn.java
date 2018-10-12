package com.mybatis.core.scan;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TableColumn {

	private String columnName;
	private String dataType;
	private String isNullAble;
	private String columnDefault;
	private String columnComment;
	
	public static TableColumn getDefaultTableColumn(ResultSet resultSet) {
		TableColumn tableColumn = null;
		try {
			while (resultSet.next()) {
				tableColumn = new TableColumn();
				tableColumn.setColumnName(resultSet.getString("columnName"));
				tableColumn.setDataType(resultSet.getString("dataType"));
				tableColumn.setIsNullAble(resultSet.getString("isNullAble"));
				tableColumn.setColumnDefault(resultSet.getString("columnDefault"));
				tableColumn.setColumnComment(resultSet.getString("columnComment"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tableColumn;
	}
	
	
	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getIsNullAble() {
		return isNullAble;
	}

	public void setIsNullAble(String isNullAble) {
		this.isNullAble = isNullAble;
	}

	public String getColumnDefault() {
		return columnDefault;
	}

	public void setColumnDefault(String columnDefault) {
		this.columnDefault = columnDefault;
	}

	public String getColumnComment() {
		return columnComment;
	}

	public void setColumnComment(String columnComment) {
		this.columnComment = columnComment;
	}

}
