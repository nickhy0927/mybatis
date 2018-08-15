package ${packages}.${domainObjectName?lower_case}.service.impl;

import org.springframework.stereotype.Service;

import com.mybatis.core.orm.common.service.impl.BaseServiceImpl;
import ${packages}.${domainObjectName?lower_case}.dao.${domainObjectName}Mapper;
import ${packages}.${domainObjectName?lower_case}.entity.${domainObjectName};
import ${packages}.${domainObjectName?lower_case}.service.${domainObjectName}Service;

@Service
public class ${domainObjectName}ServiceImpl extends BaseServiceImpl<${domainObjectName}, String, ${domainObjectName}Mapper> implements ${domainObjectName}Service{

}
