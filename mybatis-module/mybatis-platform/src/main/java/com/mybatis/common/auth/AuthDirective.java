package com.mybatis.common.auth;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@SuppressWarnings("rawtypes")
public class AuthDirective implements TemplateDirectiveModel {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private String parseHtml(String html, String alias, String spacetext) {
		StringBuilder sBuilder = new StringBuilder();
		String autHtml = "";
		sBuilder.append(autHtml.toLowerCase());
		sBuilder.append(spacetext);
		return sBuilder.toString();
	}

	private class AuthFilterWriter extends Writer {
		private final Writer out;
		private String alias;
		private String spacetext;

		public AuthFilterWriter(Writer out, String alias, String spacetext) {
			this.alias = alias;
			this.out = out;
			this.spacetext = spacetext;
		}

		@Override
		public void write(char[] cbuf, int off, int len) throws IOException {
			char[] transformedCbuf = new char[len];
			for (int i = 0; i < len; i++) {
				transformedCbuf[i] = Character.toUpperCase(cbuf[i + off]);
			}
			String noAuthHtml = new String(transformedCbuf);
			logger.info("授权前的html------ >" + noAuthHtml);
			// 通过解析标签中html内容，接口后台的权限配置数据过滤出授权后台html内容
			String rtnHtml = parseHtml(noAuthHtml, alias, spacetext);
			logger.info("授权后的html------ >" + rtnHtml);
			out.write(rtnHtml.toCharArray());
		}

		@Override
		public void flush() throws IOException {

		}

		@Override
		public void close() throws IOException {

		}

	}

	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
			throws TemplateException, IOException {
		System.out.println(params.toString());
		body.render(new AuthFilterWriter(env.getOut(), "", ""));
	}

}
