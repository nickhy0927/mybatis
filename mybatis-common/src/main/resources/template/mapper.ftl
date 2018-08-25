package ${packages}.${domainObjectName?lower_case}.dao;

import ${packages}.${domainObjectName?lower_case}.entity.${domainObjectName};
import com.mybatis.core.orm.common.mapper.BaseMapper;

public interface ${domainObjectName}Mapper extends BaseMapper<${domainObjectName},String> {

}