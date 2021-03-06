package com.mybatis.system.exceptionlog.entity;

import com.mybatis.core.orm.annotation.Column;
import com.mybatis.core.orm.annotation.Table;
import com.mybatis.core.orm.entity.IdEntity;

@Table(name = "sys_exception_log", remark = "系统异常日志")
public class ExceptionLog extends IdEntity {
	
	/**
	  * 异常信息 
	  */
    @Column(name = "exception",comment = "异常信息 ")
	private String exception;

    @Column(name = "exception_str",comment = "完整异常信息", length = 8000)
	private String exceptionStr;

    /**
     * 操作信息
     */
    @Column(name = "message",comment = "异常信息 ")
    private String message;

	/**
	  * 异常信息类型 
	  */
    @Column(name = "exception_type",comment = "异常信息类型 ")
	private Integer exceptionType;
	
	/**
	  * 方法名称
	  */
    @Column(name = "method_name",comment = "方法名称")
	private String methodName;
	
    /**
     * 获取：异常信息 
     */
    public String getException() {
       return this.exception;
    }
    
    /**
     * 设置：异常信息 
     */
    public void setException(String exception) {
       this.exception = exception;
    }

    /**
     * 获取：异常信息类型 
     */
    public Integer getExceptionType() {
       return this.exceptionType;
    }
    
    /**
     * 设置：异常信息类型 
     */
    public void setExceptionType(Integer exceptionType) {
       this.exceptionType = exceptionType;
    }

    /**
     * 获取：方法名称
     */
    public String getMethodName() {
       return this.methodName;
    }
    
    /**
     * 设置：方法名称
     */
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getExceptionStr() {
        return exceptionStr;
    }

    public void setExceptionStr(String exceptionStr) {
        this.exceptionStr = exceptionStr;
    }
}