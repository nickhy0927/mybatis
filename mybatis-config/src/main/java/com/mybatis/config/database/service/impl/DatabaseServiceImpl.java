package com.mybatis.config.database.service.impl;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.mybatis.code.meta.TableColumn;
import com.mybatis.code.util.JavaTypeResolver;
import com.mybatis.code.util.StringUtils;
import com.mybatis.common.utils.PageSupport;
import com.mybatis.common.utils.PagerInfo;
import com.mybatis.config.database.dao.DatabaseMapper;
import com.mybatis.config.database.entity.Database;
import com.mybatis.config.database.entity.TableComment;
import com.mybatis.config.database.service.DatabaseService;
import com.mybatis.core.orm.common.service.impl.BaseServiceImpl;
import com.mybatis.platform.user.entity.User;

@Service
public class DatabaseServiceImpl extends BaseServiceImpl<Database, String, DatabaseMapper> implements DatabaseService {

    @Autowired
    private DatabaseMapper databaseMapper;

    /* (non-Javadoc)
     * @see com.mybatis.config.database.service.DatabaseService#queryTableNameAndCommentByPageMap(java.util.Map, com.mybatis.common.utils.PageSupport)
     */
    @Override
    public PagerInfo<TableComment> queryTableNameAndCommentByPageMap(Map<String, Object> params, PageSupport support) {
        List<TableComment> tableComments = databaseMapper.queryTableNameAndCommentByMap(params);
        support.setTotalRecord(tableComments.size());
        params.put("startRow", support.getStartRow());
        params.put("size", support.getpageSize());
        List<TableComment> commentList = databaseMapper.queryTableNameAndCommentByPageMap(params);
        return new PagerInfo<>(support, commentList);
    }

    @Override
    public List<TableColumn> getTableColumnList(Database database, String tableName) {
        List<TableColumn> columns = Lists.newArrayList();
        JavaTypeResolver javaTypeResolver = new JavaTypeResolver();
        Connection connection = null;
        ResultSet rs = null;
        DatabaseMetaData databaseMetaData;
        TableColumn column;
        try {
            Class.forName(database.getDriverClassName());
            String connectUrl = "jdbc:mysql://" + database.getIp() + ":" + database.getPort() + "/"
                    + database.getDatabaseName() + "?" + database.getParams();
            connection = DriverManager.getConnection(connectUrl, database.getUsername(), database.getPassword());
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
                column.setJdbcTypeName(javaTypeResolver.calculateJdbcTypeName(column).toLowerCase());
                column.setJavaProperty(StringUtils.getCamelCaseString(column.getActualColumnName(), true));
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
        return columns;
    }

    @Override
    public Database queryConnectDatabase() {
        List<Database> databases = databaseMapper.queryConnectDatabase();
        if (databases != null && databases.size() > 0)
            return databases.get(0);
        return null;
    }

    @Override
    public PagerInfo<User> queryConnectUserPageByCondition(Map<String, Object> paramMap, PageSupport support) {
        return databaseMapper.queryConnectUserPageByCondition(paramMap, support);
    }
}
