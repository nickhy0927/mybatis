package com.authority.auth.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.authority.auth.handler.LoginSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private SecurityConfigParamter securityConfigParamter;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// 注册FilterSecurityInterceptor
		ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authorizeRequests = http
				.authorizeRequests();
		// 访问index.html不要权限验证
		ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry expressionInterceptUrlRegistry = authorizeRequests
				.antMatchers(securityConfigParamter.getMatcherUrls()).permitAll();
		// 其他所有路径都需要权限校验
		ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry eInterceptUrlRegistry = expressionInterceptUrlRegistry.anyRequest().authenticated();
		HttpSecurity httpSecurity = eInterceptUrlRegistry.and().csrf().disable();// 默认开启，可以显示关闭
		httpSecurity.formLogin() // 内部注册 UsernamePasswordAuthenticationFilter
				.loginPage(securityConfigParamter.getLoginPage()) // 表单登录页面地址
//				.loginProcessingUrl(securityConfigParamter.getLoginProcessingUrl())// form表单POST请求url提交地址，默认为/login
				.loginProcessingUrl("/login")
				.passwordParameter(securityConfigParamter.getPasswordParameter())// form表单用户名参数名
				.usernameParameter(securityConfigParamter.getUsernameParameter()) // form表单密码参数名
//				.successForwardUrl("/") // 登录成功跳转地址
				.failureForwardUrl(securityConfigParamter.getFailureForwardUrl()) // 登录失败跳转地址
				.defaultSuccessUrl(securityConfigParamter.getDefaultSuccessUrl())// 如果用户没有访问受保护的页面，默认跳转到页面
				.successHandler(loginSuccessHandler())
				// .failureHandler(AuthenticationFailureHandler)
				// .failureUrl("/login?error")
				.permitAll();// 允许所有用户都有权限访问loginPage，loginProcessingUrl，failureForwardUrl
	}

	/**
	 * 登录成功处理器
	 */
	private AuthenticationSuccessHandler loginSuccessHandler() {
		return new LoginSuccessHandler();
	}
}
