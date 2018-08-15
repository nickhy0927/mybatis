/**
 * 
 */
package com.mybatis.platform.menu.entity;

import com.mybatis.core.orm.annotation.Column;
import com.mybatis.core.orm.annotation.Table;
import com.mybatis.core.orm.entity.IdEntity;

/**
 * @author yuanghuangd
 * @date 2018年8月15日 上午10:43:09
 */
@Table(name = "sys_menu", remark = "")
public class Menu extends IdEntity {

	/**
	 * 菜单编号
	 */
	@Column(name = "code", comment = "菜单编号 ")
	private String code;

	/**
	 * 菜单名称
	 */
	@Column(name = "name", comment = "菜单名称 ")
	private String name;

	/**
	 * 菜单地址
	 */
	@Column(name = "url", comment = "菜单地址 ")
	private String url;

	/**
	 * 菜单别名
	 */
	@Column(name = "alias", comment = "菜单别名 ")
	private String alias;

	/**
	 * 上级菜单
	 */
	@Column(name = "menu_id", comment = "上级菜单 ")
	private String menuId;

	/**
	 * 是否启用 1 启用 0 停用
	 */
	@Column(name = "enable", comment = "是否启用 1 启用 0 停用 ")
	private Integer enable;

	/**
	 * 是否显示 1 显示 0 隐藏
	 */
	@Column(name = "shows", comment = "是否显示 1 显示 0 隐藏 ")
	private Integer shows;

	/**
	 * 信息备注
	 */
	@Column(name = "remark", comment = "信息备注")
	private String remark;

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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Menu [code=" + code + ", name=" + name + ", url=" + url + ", alias=" + alias + ", menuId=" + menuId
				+ ", enable=" + enable + ", shows=" + shows + ", remark=" + remark + "]";
	}
	
}
