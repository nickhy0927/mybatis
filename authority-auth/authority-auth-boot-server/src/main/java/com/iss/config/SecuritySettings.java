package com.iss.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 自定义安全配置类
 *
 * @Description: 自定义安全配置类
 */
@Component
public class SecuritySettings {
    /**允许访问的URL，多个用逗号分隔*/
    @Value("${securityConfig.permitall}")
    private String permitall;

    public String getPermitall() {
        return permitall;
    }

    public void setPermitall(String permitall) {
        this.permitall = permitall;
    }
}