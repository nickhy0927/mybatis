package com.mybatis.core.orm.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD }) // 作用域是类或者接口
@Retention(RetentionPolicy.RUNTIME) // 注解类型：运行时注解
public @interface Column {

	boolean isKey() default false;

	boolean createTime() default false;

	boolean updateTime() default false;

	int status() default -1;

	String comment();

	String name() default "";
	
	int lenght() default 200;
	
	boolean isLob() default false;
}
