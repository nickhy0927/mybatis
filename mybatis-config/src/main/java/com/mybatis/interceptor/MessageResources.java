package com.mybatis.interceptor;

import com.mybatis.common.singleton.UserSingleton;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;

/**
 * @program: mybatis-base
 * @description: 国际化
 * @author: Mr.Huang
 * @create: 2018-08-27 17:12
 **/
@Component
public class MessageResources {

    private ReloadableResourceBundleMessageSource resourceBundleMessageSource;

    // 获取资源文件
    public String getMessage(String key, Object[] args) {
        Locale locale = Locale.CHINA;
        HttpServletRequest request = UserSingleton.getHttpServletRequest();
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
            String message = resourceBundleMessageSource.getMessage(key, args, locale);
            System.out.println(message);
            return message;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return returnMessage;
    }

    public ReloadableResourceBundleMessageSource getResourceBundleMessageSource() {
        return resourceBundleMessageSource;
    }

    public void setResourceBundleMessageSource(ReloadableResourceBundleMessageSource resourceBundleMessageSource) {
        this.resourceBundleMessageSource = resourceBundleMessageSource;
    }
}
