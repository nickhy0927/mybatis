package com.mybatis.platform.basedata.entity;

/**
 * 菜单树
 * @author Hyuan
 *
 */
public class BaseDataTree {

	private String id;
	private String pId;
	private String name;
	private boolean open = false;

	public BaseDataTree(BaseData baseData) {
		this.id = baseData.getId();
		this.name = baseData.getName();
		this.pId = baseData.getBaseDataId();
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
