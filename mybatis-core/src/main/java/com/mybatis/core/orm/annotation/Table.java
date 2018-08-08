package com.mybatis.core.orm.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})//作用域是类或者接口  
@Retention(RetentionPolicy.RUNTIME)//注解类型：运行时注解  
public @interface Table {

	/**
	 * 表名
	 * @return
	 */
	String name();
	
	/**
	 * 备注
	 * @return
	 */
	String remark() default "";
}
