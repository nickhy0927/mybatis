package com.mybatis.interceptor;

import com.mybatis.core.orm.config.SpringContextHolder;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.Locale;

/**
 * @program: mybatis-base
 * @description: 国际化
 * @author: Mr.Huang
 * @create: 2018-08-27 17:12
 **/
public class MessageResources {

    private String[] basenames = new String[0];

    private String defaultEncoding = "ISO-8859-1";

    private boolean fallbackToSystemLocale = true;

    private int cacheMillis = -1;

    public String[] getBasenames() {
        return basenames;
    }

    public void setBasenames(String[] basenames) {
        this.basenames = basenames;
    }

    public String getDefaultEncoding() {
        return defaultEncoding;
    }

    public void setDefaultEncoding(String defaultEncoding) {
        this.defaultEncoding = defaultEncoding;
    }

    public boolean isFallbackToSystemLocale() {
        return fallbackToSystemLocale;
    }

    public void setFallbackToSystemLocale(boolean fallbackToSystemLocale) {
        this.fallbackToSystemLocale = fallbackToSystemLocale;
    }

    public int getCacheMillis() {
        return cacheMillis;
    }

    public void setCacheMillis(int cacheMillis) {
        this.cacheMillis = cacheMillis;
    }

    private static MessageResources messageResources;

    public static String getMessage(String key, Object ...args) {
        if (messageResources == null)
            messageResources = SpringContextHolder.getBean(MessageResources.class);
        return messageResources.getMessageByKey(key, args);

    }

    /**
     * 获取国际化文件中国际化字段信息
     *
     * @param key
     * @param args
     * @return
     */
    private String getMessageByKey(String key, Object[] args) {
        // 读取国际化文件
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        // 国际化文件位置
        messageSource.setBasenames(basenames);
        messageSource.setDefaultEncoding(defaultEncoding);
        messageSource.setCacheSeconds(cacheMillis);
        messageSource.setFallbackToSystemLocale(true);
        String result = "";
        try {
            // 获取默认国际化标识
            Locale locale = LocaleContextHolder.getLocale();
            // 获取国际化key-value
            result = messageSource.getMessage(key, args, "暂无国际化", locale);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String getDefaultLanguage () {
        return Locale.getDefault().toString();
    }
}
