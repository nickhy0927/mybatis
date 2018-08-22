package ${packages}.${domainObjectName?lower_case}.dao;

import ${packages}.${domainObjectName?lower_case}.entity.${domainObjectName};
import com.mybatis.core.orm.core.dao.CommonDao;

public interface ${domainObjectName}Dao extends BaseMapper<${domainObjectName},String> {

}