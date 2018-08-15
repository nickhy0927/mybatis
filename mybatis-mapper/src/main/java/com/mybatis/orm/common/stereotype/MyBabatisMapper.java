/**
 * 
 */
package com.mybatis.orm.common.stereotype;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author yuanghuangd
 * @date 2018年8月15日 下午12:50:25
 */
@Target({ ElementType.TYPE }) // 作用域是类或者接口
@Retention(RetentionPolicy.RUNTIME) // 注解类型：运行时注解
public @interface MyBabatisMapper {

	String value() default "";
}
