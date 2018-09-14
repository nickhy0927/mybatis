package com.mybatis.config.database.entity;

import com.mybatis.core.orm.annotation.Column;
import com.mybatis.core.orm.annotation.Table;
import com.mybatis.core.orm.entity.IdEntity;

@Table(name = "t_config_user_db", remark = "用户接入数据库实体")
public class UserDatabase extends IdEntity {

    @Column(comment = "用户ID")
    private String userId;

    @Column(comment = "数据库ID")
    private String databaseId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDatabaseId() {
        return databaseId;
    }

    public void setDatabaseId(String databaseId) {
        this.databaseId = databaseId;
    }
}
