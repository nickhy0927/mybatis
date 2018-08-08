package org.mybatis.auth;

import java.util.List;

public class StaticResources {

    /**
     * 登录地址
     */
    private String loginUrl;

    /**
     * 登录成功地址
     */
    private String successUrl;

    /**
     * 无权限访问地址
     */
    private String unAuthUrl;

    private List<String> staticsUrls;

    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    public String getSuccessUrl() {
        return successUrl;
    }

    public void setSuccessUrl(String successUrl) {
        this.successUrl = successUrl;
    }

    public List<String> getStaticsUrls() {
        return staticsUrls;
    }

    public void setStaticsUrls(List<String> staticsUrls) {
        this.staticsUrls = staticsUrls;
    }

    public String getUnAuthUrl() {
        return unAuthUrl;
    }

    public void setUnAuthUrl(String unAuthUrl) {
        this.unAuthUrl = unAuthUrl;
    }
}
