package com.mybatis.page.xmlop;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.core.NestedIOException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

public class MybatisXMLScanner {

	private ScheduledExecutorService service = null;

	private SqlSessionFactory sqlSession;

	private String[] mapperLocations;

	private ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();

	private final HashMap<String, String> fileMapping = new HashMap<String, String>();

	private final List<String> changeMapers = new ArrayList<String>();

	private List<File> changeXMLFiles = new ArrayList<File>();

	public MybatisXMLScanner() {
	}

	public MybatisXMLScanner(SqlSessionFactory factory, String[] mapperLocations) {
		this.sqlSession = factory;
		this.mapperLocations = mapperLocations;
	}

	public Resource[] getResource(String mapperLocation) throws IOException {

		return resourcePatternResolver.getResources(mapperLocation);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void reloadXML() throws Exception {
		Configuration configuration = sqlSession.getConfiguration();

		// 清理原有资源，更新为自己的StrictMap方便，增量重新加载
		String[] mapFieldNames = new String[] { "mappedStatements", "caches", "resultMaps", "parameterMaps",
				"keyGenerators", "sqlFragments" };
		for (String fieldName : mapFieldNames) {
			Field field = configuration.getClass().getDeclaredField(fieldName);
			field.setAccessible(true);
			Map map = ((Map) field.get(configuration));
			if (!(map instanceof StrictMap)) {
				Map newMap = new StrictMap(StringUtils.capitalize(fieldName) + "collection");
				for (Object key : map.keySet()) {
					try {
						newMap.put(key, map.get(key));
					} catch (IllegalArgumentException ex) {
						newMap.put(key, ex.getMessage());
					}
				}
				field.set(configuration, newMap);
			}
		}
		for (int i = 0; i < changeXMLFiles.size(); i++) {
			InputStream inputStream = new FileInputStream(changeXMLFiles.get(i));
			String resource = changeXMLFiles.get(i).getAbsolutePath();

			// 清理已加载的资源标识，方便让它重新加载。
			Field loadedResourcesField = configuration.getClass().getDeclaredField("loadedResources");
			loadedResourcesField.setAccessible(true);
			Set loadedResourcesSet = ((Set) loadedResourcesField.get(configuration));
			loadedResourcesSet.remove(resource);
			try {
				XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(inputStream, configuration, resource,
						configuration.getSqlFragments());
				xmlMapperBuilder.parse();
			} catch (Exception e) {
				throw new NestedIOException(
						"Failed to parse mapping resource: '" + changeXMLFiles.get(i).getAbsolutePath() + "'", e);
			} finally {
				ErrorContext.instance().reset();
			}
		}

	}

	public void scan() throws IOException {
		if (!fileMapping.isEmpty()) {
			return;
		}
		for (String mapperLocation : mapperLocations) {
			Resource[] resources = getResource(mapperLocation);
			if (resources != null) {
				for (int i = 0; i < resources.length; i++) {
					String multi_key = getValue(resources[i]);
					fileMapping.put(resources[i].getFilename(), multi_key);
				}
			}
		}
	}

	private String getValue(Resource resource) throws IOException {
		String contentLength = String.valueOf((resource.contentLength()));
		String lastModified = String.valueOf((resource.lastModified()));
		return new StringBuilder(contentLength).append(lastModified).toString();
	}

	public boolean isChanged() throws IOException {
		boolean isChanged = false;
		changeMapers.clear();
		for (String mapperLocation : mapperLocations) {
			Resource[] resources = getResource(mapperLocation);
			if (resources != null) {
				for (int i = 0; i < resources.length; i++) {
					String name = resources[i].getFilename();
					String value = fileMapping.get(name);
					String multi_key = getValue(resources[i]);
					if (!multi_key.equals(value)) {
						changeMapers.add(name);
						changeXMLFiles.add(resources[i].getFile());
						isChanged = true;
						fileMapping.put(name, multi_key);
					}
				}
			}
		}
		return isChanged;
	}

	private void mointerXmlChange() {
		service = Executors.newScheduledThreadPool(1);
		service.scheduleAtFixedRate(new MointerMybatisXMLChangeTask(this, changeMapers), 5, 5, TimeUnit.SECONDS);
	}

	public void shutDownTask() {
		if (service != null) {
			service.shutdownNow();
		}
	}

	public void scanAndMointerXmlChange() throws Exception {
		this.scan();
		this.mointerXmlChange();
	}

}