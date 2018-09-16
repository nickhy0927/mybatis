package com.iss.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.iss.mvc.interceptor.UserConfig;

@SpringBootConfiguration
public class SpringMvcInterceptor implements WebMvcConfigurer {

	@Autowired
	private UserConfig userConfig;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(userConfig).addPathPatterns("/**");
	}
}
