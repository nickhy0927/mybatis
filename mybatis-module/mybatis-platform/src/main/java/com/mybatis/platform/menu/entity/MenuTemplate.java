package com.mybatis.platform.menu.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MenuTemplate {
    /**
     * 菜单ID
     */
    private String id;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单地址
     */
    private String url;

    private String alias;

    /**
     * 国际化编码
     */
    private String localCode;

    /**
     * 上级菜单
     */
    private String menuId;

    private List<MenuTemplate> children;

    public MenuTemplate(Menu menu, List<MenuTemplate> children) {
        this.id = menu.getId();
        this.alias = menu.getAlias();
        this.name = menu.getName();
        this.url = menu.getUrl();
        this.localCode = menu.getLocalCode();
        this.menuId = menu.getMenuId();
        this.children = children;
    }

    public String getLocalCode() {
        return localCode;
    }

    public void setLocalCode(String localCode) {
        this.localCode = localCode;
    }

    public List<MenuTemplate> getChildren() {
        return children;
    }

    public void setChildren(List<MenuTemplate> children) {
        this.children = children;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    @Override
    public String toString() {
        return "MenuTemplate{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", alias='" + alias + '\'' +
                ", menuId='" + menuId + '\'' +
                ", children=" + children +
                '}';
    }
}
