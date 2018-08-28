<#list columnList as column>
${packages}.${domainObjectName?lower_case}.entity.${domainObjectName}.${column.javaProperty?uncap_first}=${column.javaProperty?uncap_first}
</#list>