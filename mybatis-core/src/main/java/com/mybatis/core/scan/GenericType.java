package com.mybatis.core.scan;

import java.lang.reflect.Field;

import com.mybatis.core.orm.annotation.Column;

public class GenericType {

	public static String getDataType(Field field, Column column) {
		String typeName = field.getGenericType().getTypeName();
		String dataType = "";
		if (typeName.equals("java.lang.String")) {
			if (column.isLob() && column.lenght() < 4000) {
				dataType = "text(" + column.lenght() + ")";
			} else if (column.isLob() && column.lenght() > 4000) {
				dataType = "mediumtext(" + column.lenght() + ")";
			} else
				dataType = "varchar(" + column.lenght() + ")";
		} else if (typeName.equals("java.util.Date")) {
			dataType = "datetime";
		} else if (typeName.equals("java.math.BigInteger")) {
			dataType = "bigint";
		} else if (typeName.equals("java.lang.Boolean")) {
			dataType = "int";
		} else if (typeName.equals("java.lang.Double")) {
			dataType = "double";
		} else if (typeName.equals("java.math.BigDecimal")) {
			dataType = "decimal(22,2)";
		} else if (typeName.equals("java.util.Timestamp")) {
			dataType = "datetime";
		} else if (typeName.equals("java.util.Time")) {
			dataType = "time";
		} else if (typeName.equals("java.lang.Short")) {
			dataType = "int";
		} else if (typeName.equals("java.lang.Float")) {
			dataType = "decimal(22,1)";
		} else if (typeName.equals("java.lang.byte[]")) {
			dataType = "blob";
		} else if (typeName.equals("java.lang.Integer")) {
			int length = column.lenght() > 8 ? 8 : column.lenght();
			dataType = "int(" + length + ")";
		}  else if (typeName.equals("java.lang.Long")) {
			dataType = "bigint";
		} else if (typeName.equals("java.lang.Character")) {
			int length = column.lenght() > 16 ? 16 : column.lenght();
			dataType = "char(" + length + ")";
		} else if (typeName.equals("java.lang.Byte")) {
			dataType = "int(8)";
		} else if (typeName.equals("int")) {
			dataType = "int(8)";
		} else if (typeName.equals("long")) {
			dataType = "bigint";
		} else if (typeName.equals("byte")) {
			dataType = "int";
		} else if (typeName.equals("short")) {
			dataType = "int";
		} else if (typeName.equals("boolean")) {
			dataType = "char";
		} else if (typeName.equals("char")) {
			dataType = "char";
		} else if (typeName.equals("float")) {
			dataType = "float";
		} else if (typeName.equals("double")) {
			dataType = "double";
		}
		return dataType;
	}
}
