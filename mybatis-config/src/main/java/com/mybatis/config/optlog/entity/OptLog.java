package com.mybatis.config.optlog.entity;

import com.mybatis.core.orm.annotation.Column;
import com.mybatis.core.orm.annotation.Table;
import com.mybatis.core.orm.entity.IdEntity;

@Table(name = "sys_opt_log", remark = "操作日志")
public class OptLog extends IdEntity{

    /**
     * 操作人名字
     */
    @Column(name = "username", comment = "操作人名字")
    private String username;

    /**
     * 操作人ID
     */
    @Column(name = "user_id", comment = "操作人ID")
    private String userId;

    /**
     * 操作信息
     */
    @Column(name = "message", comment = "操作信息", lenght = 500)
    private String message;

    /**
     * 方法
     */
    @Column(name = "method", comment = "操作信息", lenght = 500)
    private String method;

    /**
     * 操作类名称
     */
    @Column(name = "clazz", comment = "操作类名称", lenght = 100)
    private String clazz;

    /**
     *
     */
    @Column(name = "clazz", comment = "操作类名称", lenght = 1)
    public Integer optType;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public Integer getOptType() {
        return optType;
    }

    public void setOptType(Integer optType) {
        this.optType = optType;
    }

    public static class OptType {
        public static final int insert = 1;
        public static final int update = 2;
        public static final int delete = 3;
        public static final int query = 4;
    }
}


