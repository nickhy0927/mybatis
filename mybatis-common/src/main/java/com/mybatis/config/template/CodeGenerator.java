package com.mybatis.config.template;

import com.google.common.collect.Lists;
import com.mybatis.config.template.util.JavaTypeResolver;
import com.mybatis.config.template.util.StringUtils;
import com.mybatis.config.template.meta.TableColumn;
import com.mybatis.config.template.parser.GeneratorConfiguration;

import java.sql.*;
import java.util.List;

/**
 * 代码生成器
 */
public class CodeGenerator {

    public static void generate(List<GeneratorConfiguration> configurations) {
        System.out.println("开始连接数据库!");
        Connection connection = null;
        ResultSet rs = null;
        DatabaseMetaData databaseMetaData;
        TableColumn column;
        for (GeneratorConfiguration configuration : configurations) {
            List<TableColumn> columns = Lists.newArrayList();
            String tableName = configuration.getTableName();
            JavaTypeResolver javaTypeResolver = new JavaTypeResolver();
            try {
                Class.forName(configuration.getDriverClassName());
                // 获取数据库连接
                connection = DriverManager.getConnection(configuration.getConnectUrl(), configuration.getUsername(), configuration.getPassword());
                databaseMetaData = connection.getMetaData();
                // 获取表结构信息
                rs = databaseMetaData.getColumns("", "", tableName, "%");
                boolean supportsIsAutoIncrement = false;
                boolean supportsIsGeneratedColumn = false;
                ResultSetMetaData rsmd = rs.getMetaData();
                int colCount = rsmd.getColumnCount();
                for (int i = 1; i <= colCount; i++) {
                    if ("IS_AUTOINCREMENT".equals(rsmd.getColumnName(i))) {
                        supportsIsAutoIncrement = true;
                    }
                    if ("IS_GENERATEDCOLUMN".equals(rsmd.getColumnName(i))) {
                        supportsIsGeneratedColumn = true;
                    }
                }
                while (rs.next()) {
                    column = new TableColumn();
                    column.setJdbcType(rs.getInt("DATA_TYPE"));
                    column.setLength(rs.getInt("COLUMN_SIZE"));
                    column.setActualColumnName(rs.getString("COLUMN_NAME"));
                    column.setNullable(rs.getInt("NULLABLE") == DatabaseMetaData.columnNullable);
                    column.setScale(rs.getInt("DECIMAL_DIGITS"));
                    column.setRemarks(rs.getString("REMARKS"));
                    column.setDefaultValue(rs.getString("COLUMN_DEF"));

                    if (supportsIsAutoIncrement) {
                        column.setIsAutoIncrement("YES".equals(rs.getString("IS_AUTOINCREMENT")));
                    }
                    if (supportsIsGeneratedColumn) {
                        column.setIsGeneratedColumn("YES".equals(rs.getString("IS_GENERATEDCOLUMN")));
                    }
                    column.setJavaType(javaTypeResolver.get(column));
                    column.setJdbcTypeName(javaTypeResolver.calculateJdbcTypeName(column));
                    column.setJavaProperty(StringUtils.getCamelCaseString(column.getActualColumnName(), configuration.isOverwrite()));
                    columns.add(column);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (rs != null) {
                    try {
                        rs.close();
                    } catch (SQLException e) {
                        System.out.println("ResultSet 关闭失败");
                    }
                }
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        System.out.println("Connection 关闭失败");
                    }
                }
            }
            try {
                FileGenerator.writeFile(configuration, columns, configuration.getEntityName());// 写文件
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
