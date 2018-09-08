package com.mybatis.page;

import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.mybatis.page.xmlop.MybatisXMLScanner;


/**
 *	项目开发mybatis XML修改自动加载类
 *	(项目不用重启，方便开发)
 */
public class MybatisMapperDynamicLoader implements DisposableBean, InitializingBean, ApplicationContextAware {
	
	private static final Logger logger = LoggerFactory.getLogger(MybatisMapperDynamicLoader.class);  
	
	private MybatisXMLScanner scanner = null;

	private String [] mapperLocations;
	
	private SqlSessionFactory factory;
	
	public final void setMapperLocations(String[] mapperLocations) {
		this.mapperLocations = mapperLocations;
	}

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.factory = sqlSessionFactory;
	}

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		
	}

	public void afterPropertiesSet() throws Exception {
		
		if(!logger.isDebugEnabled()){
			return;
		}
		try {
			// 触发文件监听事件
			scanner = new MybatisXMLScanner(factory,mapperLocations);
			scanner.scanAndMointerXmlChange();

		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}

	public void destroy() throws Exception {
		if (scanner != null) {
			scanner.shutDownTask();
		}
	}
}