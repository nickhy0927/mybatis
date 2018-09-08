package com.mybatis.page.dialect;

import java.sql.Connection;

import org.apache.ibatis.mapping.MappedStatement;

import com.mybatis.page.entity.Page;


public abstract class Dialect {

	public static enum Type {
		MYSQL, ORACLE
	}

	public boolean supportsLimit() {
		return false;
	}

	public boolean supportsLimitOffset() {
		return supportsLimit();
	}

	public String getLimitString(String sql, int offset, int limit) {
		return getLimitString(sql, offset, Integer.toString(offset), limit, Integer.toString(limit));
	}

	public String getLimitString(String sql, int offset, String offsetPlaceholder, int limit, String limitPlaceholder) {
		throw new UnsupportedOperationException("paged queries not supported");
	}

	public void getTotalCount(Page<?> page, MappedStatement ms, Connection connection, Object parameter) {
		
	}

	public String getCountSql(String sql) {
		
		if(sql != null){
			return "select count(1) from (" + sql + ") AS total";
		}else{
			throw new UnsupportedOperationException("countsql is noExist!");
		}
	}
}