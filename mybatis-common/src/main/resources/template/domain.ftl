package ${packages}.${domainObjectName?lower_case}.entity;

<#list columnList as column>
import ${column.javaType.fullyQualifiedName};
</#list>
import com.mybatis.core.orm.annotation.Column;
import com.mybatis.core.orm.annotation.Table;
import com.mybatis.core.orm.entity.IdEntity;

@Table(name = "${tableName}", remark = "")
public class ${domainObjectName} extends IdEntity {
	
<#list columnList as column>
	
	/**
	  * ${column.remarks}
	  */
    @Column(name = "${column.actualColumnName}",comment = "${column.remarks}")
	private ${column.javaType.baseShortName?cap_first} ${column.javaProperty?uncap_first};
</#list>
	
<#list columnList as column>

    /**
     * 获取：${column.remarks}
     */
    public ${column.javaType.baseShortName?cap_first} get${column.javaProperty?cap_first}() {
       return this.${column.javaProperty?uncap_first};
    }
    
    /**
     * 设置：${column.remarks}
     */
    public void set${column.javaProperty?cap_first}(${column.javaType.baseShortName?cap_first} ${column.javaProperty?uncap_first}) {
       this.${column.javaProperty?uncap_first} = ${column.javaProperty?uncap_first};
    }
</#list>
}