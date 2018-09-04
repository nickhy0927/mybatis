package org.sso.client;

import org.sso.rpc.AuthenticationRpcService;

/**
 * 参数注入Filter
 * 
 * @author Joe
 */
public class ParamFilter {

	// 单点登录服务端URL
	protected String ssoServerUrl;

	/**
	 * 单点登录服务端提供的RPC服务，由Spring容器注入
	 */
	protected AuthenticationRpcService authenticationRpcService;
	
	public void setSsoServerUrl(String ssoServerUrl) {
		this.ssoServerUrl = ssoServerUrl;
	}
	
	/**
	 * 获取：authenticationRpcService
	 * @return the authenticationRpcService
	 */
	public AuthenticationRpcService getAuthenticationRpcService() {
		return authenticationRpcService;
	}
	
	
	/**
	 * 设置：authenticationRpcService
	 * @param authenticationRpcService the authenticationRpcService to set
	 */
	public void setAuthenticationRpcService(AuthenticationRpcService authenticationRpcService) {
		this.authenticationRpcService = authenticationRpcService;
	}
}