package com.mybatis.interceptor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 权限注解
 */
@Target({ElementType.METHOD})//作用域是类或者接口  
@Retention(RetentionPolicy.RUNTIME)//注解类型：运行时注解 
public @interface Authority {

	String alias();

}


