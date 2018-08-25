package ${packages}.${domainObjectName?lower_case}.dao.impl;

import ${packages}.${domainObjectName?lower_case}.dao.${domainObjectName}Dao;
import ${packages}.${domainObjectName?lower_case}.entity.${domainObjectName};
import com.mybatis.core.orm.core.dao.impl.CommonDaoImpl;
import org.springframework.stereotype.Repository;

@Repository
public class ${domainObjectName}DaoImpl extends CommonDaoImpl<${domainObjectName}, String> implements ${domainObjectName}Dao {

}