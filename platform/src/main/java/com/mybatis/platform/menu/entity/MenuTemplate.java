package com.mybatis.platform.menu.entity;

import java.util.List;

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

    private List<MenuTemplate> children;

    public List<MenuTemplate> getChildren() {
        return children;
    }

    public void setChildren(List<MenuTemplate> children) {
        this.children = children;
    }

    /**
     * 上级菜单
     */
    private String menuId;

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
}
