package org.spring.common.utils;

import java.util.List;

public class StaticResources {

	private List<String> urls;
	private String projectName;

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	private StaticResources() {
	}

	public List<String> getUrls() {
		return urls;
	}

	public void setUrls(List<String> urls) {
		this.urls = urls;
	}

	public static StaticResources getStaticResourcesInstance() {
		return SpringContextHolder.getBean(StaticResources.class);
	}

}
