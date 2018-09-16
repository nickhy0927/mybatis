package com.authority.auth.filter;

public class SecurityConfigParamter {

	/**
	 * 不需要拦截的url
	 */
	private String matcherUrls;
	
	/**
	 * 表单登录页面地址
	 */
	private String loginPage;
	
	/**
	 * 登录请求地址
	 */
	private String loginProcessingUrl;
	
	/**
	 * form表单用户名参数名
	 */
	private String usernameParameter;
	
	/**
	 * form表单密码参数名
	 */
	private String passwordParameter;
	
	/**
	 * 登录失败跳转地址
	 */
	private String failureForwardUrl;
	
	/**
	 * 如果用户没有访问受保护的页面，默认跳转到页面
	 */
	private String defaultSuccessUrl;

	public String getMatcherUrls() {
		return matcherUrls;
	}

	public void setMatcherUrls(String matcherUrls) {
		this.matcherUrls = matcherUrls;
	}

	public String getLoginPage() {
		return loginPage;
	}

	public void setLoginPage(String loginPage) {
		this.loginPage = loginPage;
	}

	public String getLoginProcessingUrl() {
		return loginProcessingUrl;
	}

	public void setLoginProcessingUrl(String loginProcessingUrl) {
		this.loginProcessingUrl = loginProcessingUrl;
	}

	public String getUsernameParameter() {
		return usernameParameter;
	}

	public void setUsernameParameter(String usernameParameter) {
		this.usernameParameter = usernameParameter;
	}

	public String getPasswordParameter() {
		return passwordParameter;
	}

	public void setPasswordParameter(String passwordParameter) {
		this.passwordParameter = passwordParameter;
	}

	public String getFailureForwardUrl() {
		return failureForwardUrl;
	}

	public void setFailureForwardUrl(String failureForwardUrl) {
		this.failureForwardUrl = failureForwardUrl;
	}

	public String getDefaultSuccessUrl() {
		return defaultSuccessUrl;
	}

	public void setDefaultSuccessUrl(String defaultSuccessUrl) {
		this.defaultSuccessUrl = defaultSuccessUrl;
	}
	
}
