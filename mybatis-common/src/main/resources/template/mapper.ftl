package ${packages}.${domainObjectName?lower_case}.dao;

import ${packages}.${domainObjectName?lower_case}.entity.${domainObjectName};
import org.springframework.stereotype.Repository;
import com.mybatis.core.orm.common.mapper.BaseMapper;

@Repository
public interface ${domainObjectName}Mapper extends BaseMapper<${domainObjectName},String> {

}