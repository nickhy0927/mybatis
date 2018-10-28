package com.mybatis.common.auth;

import java.io.IOException;

import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.iss.shiro.freemarker.ShiroTags;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;

public class ShiroFreeMarkerConfigurer  extends FreeMarkerConfigurer {

	@Override
	public void afterPropertiesSet() throws IOException, TemplateException {
		Configuration cfg = this.getConfiguration();
		cfg.setSharedVariable("shiro", new ShiroTags());// shiro标签
		cfg.setNumberFormat("#");// 防止页面输出数字,变成2,000
		// 可以添加很多自己的要传输到页面的[方法、对象、值]
	}
}
