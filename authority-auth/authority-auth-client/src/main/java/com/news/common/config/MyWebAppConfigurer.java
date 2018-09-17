package com.news.common.config;

import com.news.common.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebAppConfigurer implements WebMvcConfigurer {

    @Bean   //把我们的拦截器注入为bean
    public HandlerInterceptor getMyInterceptor(){
        return new LoginInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // addPathPatterns 用于添加拦截规则, 这里假设拦截 /url 后面的全部链接
        // excludePathPatterns 用户排除拦截
        InterceptorRegistration registration = registry.addInterceptor(getMyInterceptor());
        //排除的路径
        registration.excludePathPatterns("/error");
        registration.excludePathPatterns("/login**");
        //拦截所有路径
        registration.addPathPatterns("/**");
    }
}
