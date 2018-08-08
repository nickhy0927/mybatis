package com.mybatis.config.table;

import com.mybatis.core.orm.annotation.Column;
import com.mybatis.core.orm.annotation.Table;
import com.mybatis.core.orm.entity.IdEntity;

@Table(name = "t_template_table", remark = "数据库实体对象参数管理")
public class TemplateTable extends IdEntity {

	/**
	 * 文件路径
	 */
	@Column(comment = "文件路径")
	private String path;

	/**
	 * 实体名称
	 */
	@Column(comment = "实体名称")
	private String entityName;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

}
