package com.mybatis.code.parser;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

@SuppressWarnings("unchecked")
public class DomParser {

	public static List<GeneratorConfiguration> generatorConfigurations = new ArrayList<>();
	
	private DataSource dataSource;
	
	public void init() throws Exception {
		SAXReader reader = new SAXReader();
		URL url = DomParser.class.getResource("/conf/generatorConfig.xml");
		if (url == null) {
			System.out.println("获取配置文件路径无法找到...");
		} else {
			Document document = reader.read(new File(url.getFile()));
			Element element = document.getRootElement();
			Element connection = element.element("connection");
			String driverClassName = connection.element("diver.class.name").getStringValue();
			String connetionUrl = connection.element("connect.url").getStringValue();
			String username = connection.element("username").getStringValue();
			String password = connection.element("password").getStringValue();
			Element config = element.element("config");
			String projectPath = config.attributeValue("projectPath");
			List<Element> elements = config.elements();
			for (Element ele : elements) {
				GeneratorConfiguration generatorConfiguration = generatorConfiguration(ele);
				generatorConfiguration.setProjectPath(projectPath);
				generatorConfiguration.setDriverClassName(driverClassName);
				generatorConfiguration.setConnectUrl(connetionUrl);
				generatorConfiguration.setUsername(username);
				generatorConfiguration.setPassword(password);
				String baseDir = ele.attributeValue("baseDir");
				generatorConfiguration.setBaseDir(baseDir );
				String target = ele.attributeValue("target");
				generatorConfiguration.setTarget(target);
				generatorConfiguration.setOverwrite(Boolean.valueOf(ele.attributeValue("overwrite")));
				generatorConfiguration.setList(Boolean.valueOf(ele.attributeValue("list")));
				generatorConfiguration.setCreate(Boolean.valueOf(ele.attributeValue("create")));
				generatorConfiguration.setEdit(Boolean.valueOf(ele.attributeValue("edit")));
				generatorConfigurations.add(generatorConfiguration);
			}
		}
	}

	private GeneratorConfiguration generatorConfiguration(Element ele) {
		GeneratorConfiguration generatorConfiguration = new GeneratorConfiguration();
		Element element = ele.element("java.file.package");
		if (element != null)
			generatorConfiguration.setPackages(element.getStringValue());
		element = ele.element("sql.mapper.path");
		if (element != null)
			generatorConfiguration.setSqlMapperPath(element.getStringValue());
		element = ele.element("entity.name");
		if (element != null)
			generatorConfiguration.setEntityName(element.getStringValue());
		element = ele.element("table.name");
		if (element != null)
			generatorConfiguration.setTableName(element.getStringValue());
		element = ele.element("entity.write");
		if (element != null)
			generatorConfiguration.setEntitywrite(Boolean.valueOf(element.getStringValue()));
		element = ele.element("dao.write");
		if (element != null)
			generatorConfiguration.setDaowrite(Boolean.valueOf(element.getStringValue()));
		element = ele.element("service.write");
		if (element != null)
			generatorConfiguration.setServicewrite(Boolean.valueOf(element.getStringValue()));
		element = ele.element("controller.write");
		if (element != null)
			generatorConfiguration.setControllerwrite(Boolean.valueOf(element.getStringValue()));
		return generatorConfiguration;
	}
	
	/**
	 * @return the dataSource
	 */
	public DataSource getDataSource() {
		return dataSource;
	}
	
	/**
	 * @param dataSource the dataSource to set
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
}
