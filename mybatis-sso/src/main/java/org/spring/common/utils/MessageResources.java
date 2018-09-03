package org.spring.common.utils;

import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.support.ResourceBundleMessageSource;

public class MessageResources {

	private static MessageResources messageResources = null;
	
	private ResourceBundleMessageSource messageSource;

	public static ResourceBundleMessageSource getBundleMessageSources(ServletContext context) {
		if (messageResources == null) {
			messageResources = SpringContextHolder.getBean(MessageResources.class);
		}
		return messageResources.getMessageSource();
	}

	// 获取资源文件
	public static String getMessage(HttpServletRequest request, String key, Object[] args) {
		Locale locale = Locale.CHINA;
		HttpSession sessioin = request.getSession();
		String language = null;
		if (sessioin.getAttribute("language") != null) {
			language = (String) sessioin.getAttribute("language");
		}
		if (language == null || language.equals("zh_CN")) {
			locale = Locale.CHINA;
		} else {
			if (language.equals("en_US")) {
				locale = Locale.US;
			} else if (language.equals("ja-JP")) {
				locale = Locale.JAPAN;
			} else if (language.equals("ko-KR")) {
				locale = Locale.KOREA;
			}
		}
		String returnMessage = "";
		try {
			ResourceBundleMessageSource messageSources = getBundleMessageSources(request.getServletContext());
			String message = messageSources.getMessage(key, args, locale);
			System.out.println(message);
			return message;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return returnMessage;
	}

	public ResourceBundleMessageSource getMessageSource() {
		return messageSource;
	}
	
	public void setMessageSource(ResourceBundleMessageSource messageSource) {
		this.messageSource = messageSource;
	}
}
