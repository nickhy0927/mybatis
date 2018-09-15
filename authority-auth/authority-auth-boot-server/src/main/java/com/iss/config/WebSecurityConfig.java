package com.iss.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * Security安全配置
 * @Description: Security安全配置
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    protected SecuritySettings securitySettings;     // 自定义安全配置类

    /**
     * 注册UserDetailsService的bean
     */
    @Bean
    public UserDetailsService customUserService() {
        return new CustomUserService();
    }

    /**
     * 登录认证
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserService()); //userDetailsService验证

    }

    /***设置不拦截规则*/
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/static/**", "/druid/**");
    }

    /**
     * 安全策略配置
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 设置游客可以访问的URI
        if (StringUtils.isNotBlank(securitySettings.getPermitall())) {
            http.authorizeRequests().antMatchers(securitySettings.getPermitall().split(",")).permitAll();
        }

        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authenticated = http.authorizeRequests().anyRequest().authenticated();//任何请求,登录后可以访问
        // 配置登录URI、登录失败跳转URI与登录成功后默认跳转URI
        FormLoginConfigurer<HttpSecurity> configurer = authenticated.and().formLogin();
        configurer.loginPage("/login");
        FormLoginConfigurer<HttpSecurity> defaultSuccessUrl = configurer.failureUrl("/login ?error").permitAll().defaultSuccessUrl("/", true);
        defaultSuccessUrl.successHandler(loginSuccessHandler());
        // 注销行为任意访问
        defaultSuccessUrl.and().logout().permitAll();
        // 设置拒绝访问的提示URI
        defaultSuccessUrl.and().exceptionHandling().accessDeniedPage("/login?illegal");
    }

    /**
     * 登录成功处理器
     */
    private AuthenticationSuccessHandler loginSuccessHandler() {
        return new LoginSuccessHandler();
    }
}
