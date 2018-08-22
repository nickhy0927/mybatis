package ${packages}.${domainObjectName?lower_case}.service;

import com.mybatis.core.orm.common.mapper.BaseMapper;
import ${packages}.${domainObjectName?lower_case}.entity.${domainObjectName};

public interface ${domainObjectName}Service extends BaseMapper<${domainObjectName}, String> {

}
