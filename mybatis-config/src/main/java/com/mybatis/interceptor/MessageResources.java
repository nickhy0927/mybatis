package com.mybatis.interceptor;

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

    /**
     * 获取国际化文件中国际化字段信息
     * @param key
     * @param args
     * @return
     */
    public static String getMessageByKey(String key, Object[] args) {

        // 读取国际化文件
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        // 国际化文件位置
        messageSource.setBasenames("i18n.common");
        messageSource.setDefaultEncoding("utf-8");
        messageSource.setCacheSeconds(30000);
        messageSource.setFallbackToSystemLocale(true);
        String result = "";
        try {
            // 获取默认国际化标识
            Locale locale = LocaleContextHolder.getLocale();
            System.out.println("locale:===>" + locale);
            // 获取国际化key-value
            result = messageSource.getMessage(key, args, "暂无国际化", locale);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
