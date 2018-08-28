<#list columnList as column>
${domainObjectName}.${column.javaProperty?uncap_first}=${column.remarks}
</#list>

${domainObjectName}.create=新增
${domainObjectName}.search=查询
${domainObjectName}.savesure=确定要保存吗？
${domainObjectName}.deletesure=确定要删除吗？

${domainObjectName}.save.success=保存信息成功
${domainObjectName}.save.fail=保存信息失败

${domainObjectName}.edit.success=修改信息成功
${domainObjectName}.edit.fail=修改信息失败

${domainObjectName}.delete.success=删除信息成功
${domainObjectName}.delete.fail=删除信息失败

${domainObjectName}.list.success=查询列表信息成功
${domainObjectName}.list.fail=查询列表信息失败
