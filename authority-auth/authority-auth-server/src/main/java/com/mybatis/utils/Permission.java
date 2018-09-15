package com.mybatis.utils;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

@SuppressWarnings("serial")
public class Permission extends BodyTagSupport {

	private String alias;

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	@Override
	public int doStartTag() throws JspException {
//		InitEnvironment environment = InitEnvironment.getInitEnvironmentInstance();
//		// 获取session中存放的权限
//		User user = Singleton.getSingletonUser();
//		if (StringUtils.equals(user.getLoginName(), environment.getInitUsername())) {
//			return BodyTagSupport.EVAL_BODY_INCLUDE;
//		} else {
//			Set<String> alis = Singleton.getMenuAliasList();
//			if (alis.contains(alias)) {
//				return BodyTagSupport.EVAL_BODY_INCLUDE;// 返回此则执行标签body中内容，SKIP_BODY则不执行
//			} else
//				return BodyTagSupport.SKIP_BODY;
//
//		}
		return BodyTagSupport.SKIP_BODY;
	}
}
