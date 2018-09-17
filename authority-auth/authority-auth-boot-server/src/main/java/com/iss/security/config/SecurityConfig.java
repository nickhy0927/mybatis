package com.iss.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import com.iss.security.filter.CustomAccessDecisionManager;
import com.iss.security.filter.CustomMetadataSourceService;
import com.iss.security.filter.CustomSecurityInterceptor;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		// 取消csrf
        http.csrf().disable()
        // 基于token，所以不需要session
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
        .authorizeRequests()
        .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
        // 允许对于网站静态资源的无授权访问
        .antMatchers(
                HttpMethod.GET,
                "/",
                "/*.html",
                "/static/**",
                "/favicon.ico",
                "/**/*.html",
                "/assets/**",
                "/**/*.css",
                "/**/*.js",
                "/login.do",
                "/webjars/**",
                "/swagger-resources/**",
                "/*/api-docs"
        ).permitAll()
        // 除上面外的所有请求全部需要鉴权认证
        .anyRequest().authenticated().and().formLogin().loginPage("/login.do");
	}

	@Bean
	public CustomSecurityInterceptor getCustomSecurityInterceptor() {
		CustomSecurityInterceptor interceptor = new CustomSecurityInterceptor();
		interceptor.setAccessDecisionManager(getCustomAccessDecisionManager());
		interceptor.setSecurityMetadataSource(getCustomMetadataSourceService());
		try {
			interceptor.setAuthenticationManager(this.authenticationManagerBean());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return interceptor;
	}

	@Bean
	public CustomAccessDecisionManager getCustomAccessDecisionManager() {
		return new CustomAccessDecisionManager();
	}

	@Bean
	public CustomMetadataSourceService getCustomMetadataSourceService() {
		CustomMetadataSourceService sourceService = new CustomMetadataSourceService();
		return sourceService;
	}
}
