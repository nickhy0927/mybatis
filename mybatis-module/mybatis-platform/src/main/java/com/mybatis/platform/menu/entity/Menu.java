package com.mybatis.platform.menu.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mybatis.core.orm.annotation.Column;
import com.mybatis.core.orm.annotation.Table;
import com.mybatis.core.orm.entity.IdEntity;

@Table(name = "sys_menu", remark = "菜单")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Menu extends IdEntity {
	
	/**
	  * 菜单编号 
	  */
    @Column(name = "code",comment = "菜单编号 ")
	private String code;
	
	/**
	  * 菜单名称 
	  */
    @Column(name = "name",comment = "菜单名称 ")
	private String name;
	
	/**
	  * 菜单地址 
	  */
    @Column(name = "url",comment = "菜单地址 ")
	private String url;
	
	/**
	  * 菜单别名 
	  */
    @Column(name = "alias",comment = "菜单别名 ")
	private String alias;
	
	/**
	  * 上级菜单 
	  */
    @Column(name = "menu_id",comment = "上级菜单 ")
	private String menuId;
	
	/**
	  * 是否启用 1 启用 0 停用 
	  */
    @Column(name = "enable",comment = "是否启用 1 启用 0 停用 ")
	private Integer enable;
	
	/**
	  * 是否显示 1 显示 0 隐藏 
	  */
    @Column(name = "shows",comment = "是否显示 1 显示 0 隐藏 ")
	private Integer shows;

    /**
     * 国际化编码
     */
    @Column(name = "local_code",comment = "国际化编码")
    private String localCode;

	/**
	  * 信息备注
	  */
    @Column(name = "remark",comment = "信息备注")
	private String remark;

    @Column(name = "sort",comment = "菜单排序")
    private Integer sort;

    /**
     * 是否主页显示
     */
    @Column(comment = "是否主页显示 1 是 0 否")
    private Integer showIndex;

    /**
     * 父节点菜单名称
     */
    private String parentName;

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    /**
     * 获取：菜单编号 
     */
    public String getCode() {
       return this.code;
    }
    
    /**
     * 设置：菜单编号 
     */
    public void setCode(String code) {
       this.code = code;
    }

    /**
     * 获取：菜单名称 
     */
    public String getName() {
       return this.name;
    }
    
    /**
     * 设置：菜单名称 
     */
    public void setName(String name) {
       this.name = name;
    }

    /**
     * 获取：菜单地址 
     */
    public String getUrl() {
       return this.url;
    }
    
    /**
     * 设置：菜单地址 
     */
    public void setUrl(String url) {
       this.url = url;
    }

    /**
     * 获取：菜单别名 
     */
    public String getAlias() {
       return this.alias;
    }
    
    /**
     * 设置：菜单别名 
     */
    public void setAlias(String alias) {
       this.alias = alias;
    }

    /**
     * 获取：上级菜单 
     */
    public String getMenuId() {
       return this.menuId;
    }
    
    /**
     * 设置：上级菜单 
     */
    public void setMenuId(String menuId) {
       this.menuId = menuId;
    }

    /**
     * 获取：是否启用 1 启用 0 停用 
     */
    public Integer getEnable() {
       return this.enable;
    }
    
    /**
     * 设置：是否启用 1 启用 0 停用 
     */
    public void setEnable(Integer enable) {
       this.enable = enable;
    }

    /**
     * 获取：是否显示 1 显示 0 隐藏 
     */
    public Integer getShows() {
       return this.shows;
    }
    
    /**
     * 设置：是否显示 1 显示 0 隐藏 
     */
    public void setShows(Integer shows) {
       this.shows = shows;
    }

    /**
     * 获取：信息备注
     */
    public String getRemark() {
       return this.remark;
    }
    
    /**
     * 设置：信息备注
     */
    public void setRemark(String remark) {
       this.remark = remark;
    }

    /**
     * 获取：菜单排序
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置：菜单排序
     * @param sort
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 获取：是否主页显示
     * @return
     */
    public Integer getShowIndex() {
        return showIndex;
    }

    /**
     * 设置：是否主页显示
     * @param showIndex
     */
    public void setShowIndex(Integer showIndex) {
        this.showIndex = showIndex;
    }

    /**
     * 设置：国际化编码
     * @return
     */
    public String getLocalCode() {
        return localCode;
    }

    /**
     * 设置：国际化编码
     * @param localCode
     */
    public void setLocalCode(String localCode) {
        this.localCode = localCode;
    }
}