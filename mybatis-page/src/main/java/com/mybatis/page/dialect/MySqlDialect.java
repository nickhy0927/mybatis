package com.mybatis.page.dialect;

public class MySqlDialect extends Dialect {

	public boolean supportsLimitOffset() {
		return true;
	}

	public boolean supportsLimit() {
		return true;
	}

	public String getLimitString(String sql, int offset, int limit) {
		return getLimitString(sql, offset, String.valueOf(offset), limit, String.valueOf(limit));
	}

	public String getLimitString(String sql, int offset, String offsetPlaceholder, int limit, String limitPlaceholder) {
		if (offset > 0) {
			return sql + " limit " + offsetPlaceholder + "," + limitPlaceholder;
		} else {
			return sql + " limit " + limitPlaceholder;
		}
	}

}
