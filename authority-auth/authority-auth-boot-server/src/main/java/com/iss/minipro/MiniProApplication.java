package com.iss.minipro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.iss")
@MapperScan("com.iss.module.**.mapper")
//@ImportResource({"classpath:dubbo-customer.xml","classpath:application-redis.xml"})
public class MiniProApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(MiniProApplication.class, args);
	}
}
