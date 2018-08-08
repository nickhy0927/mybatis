package com.mybatis.config.database.entity;

public class TableComment {
    /**
     * 表名称
     */
    private String tableName;

    /**
     * 表注释
     */
    private String tableComment;


    public String getTableComment() {
        return tableComment;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
