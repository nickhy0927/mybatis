package com.mybatis.platform.menu.entity;

/**
 * 菜单树
 * @author Hyuan
 *
 */
public class MenuTree {

	private String id;
	private String pId;
	private String name;
	private boolean open = false;
	
	public MenuTree(Menu menu) {
		this.id = menu.getId();
		this.name = menu.getName();
		this.pId = menu.getMenuId();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}
	
	
}
