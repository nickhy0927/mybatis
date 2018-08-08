package ${packages}.${domainObjectName?lower_case}.service;

import ${packages}.${domainObjectName?lower_case}.entity.${domainObjectName};
import com.mybatis.core.orm.core.service.CommonService;
import org.springframework.stereotype.Service;

@Service
public class ${domainObjectName}Service extends CommonService<${domainObjectName}, String>{

}
