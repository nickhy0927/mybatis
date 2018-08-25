package com.mybatis.config.exceptionlog;

import com.mybatis.core.orm.annotation.Column;
import com.mybatis.core.orm.annotation.Table;
import com.mybatis.core.orm.entity.IdEntity;

@Table(name = "sys_exception_log", remark = "操作日志")
public class ExceptionLog extends IdEntity {

    /**
     * 异常信息
     */
    @Column(name = "exception", comment = "异常信息", lenght = 4000)
    private String exception;

    /**
     * 异常信息类型
     */
    @Column(name = "exception_type", comment = "异常信息类型", lenght = 1)
    private Integer exceptionType;

    /**
     * 方法名称
     */
    @Column(name = "method_name", comment = "方法名称", lenght = 1)
    private String methodName;


    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public Integer getExceptionType() {
        return exceptionType;
    }

    public void setExceptionType(Integer exceptionType) {
        this.exceptionType = exceptionType;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public static class ExceptionType {
        /**
         * 业务异常
         */
        public static int BUSINESS = 1;

        /**
         * 系统异常
         */
        public static int SYSTEM = 2;

    }
}
