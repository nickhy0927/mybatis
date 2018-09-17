package com.iss.security.filter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

/**
 * 获取请求url需要的权限
 */
@Component
public class CustomMetadataSourceService implements FilterInvocationSecurityMetadataSource {

	private PathMatcher matcher = new AntPathMatcher();

	private String indexUrl = "/index.jsp";

	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		// 获取当前访问url
		String url = ((FilterInvocation) object).getRequestUrl();
		int firstQuestionMarkIndex = url.indexOf("?");
		if (firstQuestionMarkIndex != -1) {
			url = url.substring(0, firstQuestionMarkIndex);
		}
		List<ConfigAttribute> result = new ArrayList<>();
		// 如果不是拦截列表里的
		if (!isIntercept(url)) {
			ConfigAttribute attribute = new SecurityConfig("ROLE_ANONYMOUS");
			result.add(attribute);
			return result;
		}
		return result;
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return true;
	}

	/**
	 * 判断是否需要过滤
	 * 
	 * @param url
	 * @return
	 */
	public boolean isIntercept(String url) {
		String[] filterPaths = {"/auth/**", "/login.do", "/static/**"};
		for (String filter : filterPaths) {
			if (matcher.match(filter, url) & !matcher.match(indexUrl, url)) {
				return true;
			}
		}
		return false;
	}

	public String getIndexUrl() {
		return indexUrl;
	}

	public void setIndexUrl(String indexUrl) {
		this.indexUrl = indexUrl;
	}
}
