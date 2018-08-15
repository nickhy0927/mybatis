package com.mybatis.code;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.druid.util.StringUtils;
import com.mybatis.code.meta.TableColumn;
import com.mybatis.code.parser.GeneratorConfiguration;
import com.mybatis.code.util.Underline2Camel;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

/**
 * 文件生成器
 */
public class FileGenerator {

	private static String sf = "%s/%s/%s%s";

	/**
	 * 获取freemarker配置信息
	 *
	 * @return
	 * @throws IOException
	 */
	private static Configuration getConfig() throws IOException {
		Configuration cfg = new Configuration();
		String ftlPath = FileGenerator.class.getResource("/META-INF/template").getPath();
		cfg.setDirectoryForTemplateLoading(new File(ftlPath)); // 需要文件夹绝对路径
		cfg.setDefaultEncoding("UTF-8");
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		return cfg;
	}

	public static void writeFile(GeneratorConfiguration configuration, List<TableColumn> columns,
			String domainObjectName) throws Exception {
		Map<String, Object> root = new HashMap<>();
		root.put("domainObjectName", domainObjectName);
		root.put("config", configuration);
		root.put("tableName", configuration.getTableName());
		root.put("packages", configuration.getPackages());
		root.put("columnList", columns);
		if (configuration.isEntitywrite()) {
			domainFile(root, configuration);
		}
		if(configuration.getTemplateType() == 1) {
			if (configuration.isDaowrite()) {
				daoFile(root, configuration);
				daoImplFile(root, configuration);
				xmlFile(root, configuration);
			}
			if (configuration.isServicewrite()) {
				serviceFile(root, configuration);
			}
		} else {
			if (configuration.isDaowrite()) {
				mapperFile(root, configuration);
				mapperXmlFile(root, configuration);
			}
			if (configuration.isServicewrite()) {
				mapperServiceFile(root, configuration);
				mapperServiceImplFile(root, configuration);
			}
		}
		if (configuration.isControllerwrite()) {
			controllerFile(root, configuration);
		}

		if (configuration.isList()) {
			listFile(root, configuration);
		}
		if (configuration.isCreate()) {
			createFile(root, configuration);
		}
		if (configuration.isEdit()) {
			editFile(root, configuration);
		}
	}

	/**
	 * 生成实体类
	 *
	 * @param root
	 * @throws Exception
	 */
	private static void domainFile(Map<String, Object> root, GeneratorConfiguration configuration) throws Exception {
		Configuration cfg = getConfig();
		String suffix = ".java";
		String projectPath = configuration.getProjectPath();
		String packagePath = getPackages(configuration) + "/" + getDomainObjectName(configuration.getEntityName()) + "/entity";
		String template = "domain.ftl";
		String domainObjectName = configuration.getEntityName();
		writeSingleFile(cfg, root, template, projectPath, packagePath, domainObjectName, suffix,
				configuration.isDaowrite());
	}

	private static String getPackages(GeneratorConfiguration configuration) {
		String javaFilePackageStr = configuration.getPackages();
		return javaFilePackageStr.replace(".", "/");
	}

	/**
	 * 生成dao接口
	 *
	 * @param root
	 * @throws Exception
	 */
	private static void daoFile(Map<String, Object> root, GeneratorConfiguration configuration) throws Exception {
		Configuration cfg = getConfig();
		String suffix = "Dao.java";
		String projectPath = configuration.getProjectPath();
		String domainObjectName = getDomainObjectName(configuration.getEntityName());
		String packagePath = getPackages(configuration) + "/" + domainObjectName + "/dao";
		String template = "dao.ftl";
		writeSingleFile(cfg, root, template, projectPath, packagePath, configuration.getEntityName(), suffix,
				configuration.isDaowrite());
	}
	private static void mapperFile(Map<String, Object> root, GeneratorConfiguration configuration) throws Exception {
		Configuration cfg = getConfig();
		String suffix = "Mapper.java";
		String projectPath = configuration.getProjectPath();
		String domainObjectName = getDomainObjectName(configuration.getEntityName());
		String packagePath = getPackages(configuration) + "/" + domainObjectName + "/dao";
		String template = "mapper.ftl";
		writeSingleFile(cfg, root, template, projectPath, packagePath, configuration.getEntityName(), suffix,
				configuration.isDaowrite());
	}

	/**
	 * 生成dao实现类
	 *
	 * @param root
	 * @throws Exception
	 */
	private static void daoImplFile(Map<String, Object> root, GeneratorConfiguration configuration) throws Exception {
		Configuration cfg = getConfig();
		String suffix = "DaoImpl.java";
		String projectPath = configuration.getProjectPath();
		String javaFilePackage = getPackages(configuration);
		String domainObjectName = configuration.getEntityName();
		String packagePath = javaFilePackage + "/" + getDomainObjectName(domainObjectName) + "/dao/impl";
		String template = "daoImpl.ftl";
		writeSingleFile(cfg, root, template, projectPath, packagePath, domainObjectName, suffix,
				configuration.isDaowrite());
	}

	private static void serviceFile(Map<String, Object> root, GeneratorConfiguration configuration) throws Exception {
		Configuration cfg = getConfig();
		String suffix = "Service.java";
		String projectPath = configuration.getProjectPath();
		String packagePath = getPackages(configuration) + "/" + getDomainObjectName(configuration.getEntityName()) + "/service";
		String template = "service.ftl";
		writeSingleFile(cfg, root, template, projectPath, packagePath, configuration.getEntityName(), suffix,
				configuration.isServicewrite());
	}
	
	private static void mapperServiceFile(Map<String, Object> root, GeneratorConfiguration configuration) throws Exception {
		Configuration cfg = getConfig();
		String suffix = "Service.java";
		String projectPath = configuration.getProjectPath();
		String packagePath = getPackages(configuration) + "/" + getDomainObjectName(configuration.getEntityName()) + "/service";
		String template = "mapperService.ftl";
		writeSingleFile(cfg, root, template, projectPath, packagePath, configuration.getEntityName(), suffix,
				configuration.isServicewrite());
	}
	
	private static void mapperServiceImplFile(Map<String, Object> root, GeneratorConfiguration configuration) throws Exception {
		Configuration cfg = getConfig();
		String suffix = "ServiceImpl.java";
		String projectPath = configuration.getProjectPath();
		String packagePath = getPackages(configuration) + "/" + getDomainObjectName(configuration.getEntityName()) + "/service/impl";
		String template = "mapperServiceImpl.ftl";
		writeSingleFile(cfg, root, template, projectPath, packagePath, configuration.getEntityName(), suffix,
				configuration.isServicewrite());
	}

	private static void controllerFile(Map<String, Object> root, GeneratorConfiguration configuration)
			throws Exception {
		Configuration cfg = getConfig();
		String suffix = "Controller.java";
		String projectPath = configuration.getProjectPath();
		String packagePath = getPackages(configuration) + "/" + getDomainObjectName(configuration.getEntityName())+ "/controller";
		String template = "controller.ftl";
		String page = Underline2Camel.camel2Centerine(configuration.getEntityName()).toLowerCase();
		String pagePath = configuration.getTarget() + "/" + page;
		String url = configuration.getTarget().replaceAll("module/", "").toLowerCase();
		root.put("accessPath", url + "/" + page);
		if (StringUtils.isEmpty(configuration.getTarget())) {
			pagePath = configuration.getEntityName().toLowerCase() + "/" + page;
		}
		root.put("pagePath", pagePath);
		writeSingleFile(cfg, root, template, projectPath, packagePath, configuration.getEntityName(), suffix,
				configuration.isControllerwrite());
	}

	private static void listFile(Map<String, Object> root, GeneratorConfiguration configuration) throws Exception {
		Configuration cfg = getConfig();
		String suffix = "-list.ftl";
		String projectPath = configuration.getBaseDir() + configuration.getTarget();
		String domainObjectName = Underline2Camel.camel2Centerine(configuration.getEntityName());
		String template = "list.ftl";
		root.put("ctx", "${pageContext.request.contextPath}");
		String page = Underline2Camel.camel2Centerine(configuration.getEntityName()).toLowerCase();
		String url = configuration.getTarget().replaceAll("module/", "").toLowerCase();
		root.put("accessPath", url + "/" + page);
		writeSingleFile(cfg, root, template, projectPath, "", domainObjectName.toLowerCase(), suffix,
				configuration.isList());
	}

	private static void createFile(Map<String, Object> root, GeneratorConfiguration configuration) throws Exception {
		Configuration cfg = getConfig();
		String suffix = "-create.ftl";
		String projectPath = configuration.getBaseDir() + configuration.getTarget();
		String domainObjectName = Underline2Camel.camel2Centerine(configuration.getEntityName());
		String template = "create.ftl";
		String page = Underline2Camel.camel2Centerine(configuration.getEntityName()).toLowerCase();
		String url = configuration.getTarget().replaceAll("module/", "").toLowerCase();
		root.put("accessPath", url + "/" + page);
		root.put("ctx", "${pageContext.request.contextPath}");
		writeSingleFile(cfg, root, template, projectPath, "", domainObjectName.toLowerCase(), suffix,
				configuration.isCreate());
	}

	private static void editFile(Map<String, Object> root, GeneratorConfiguration configuration) throws Exception {
		Configuration cfg = getConfig();
		String suffix = "-edit.ftl";
		String projectPath = configuration.getBaseDir() + configuration.getTarget();
		String domainObjectName = Underline2Camel.camel2Centerine(configuration.getEntityName());
		String template = "edit.ftl";
		String page = Underline2Camel.camel2Centerine(configuration.getEntityName()).toLowerCase();
		String url = configuration.getTarget().replaceAll("module/", "").toLowerCase();
		root.put("accessPath", url + "/" + page);
		root.put("ctx", "${pageContext.request.contextPath}");
		writeSingleFile(cfg, root, template, projectPath, "", domainObjectName.toLowerCase(), suffix,
				configuration.isEdit());
	}

	/**
	 * 生成mybatis xml文件
	 *
	 * @param root
	 * @throws Exception
	 */
	private static void xmlFile(Map<String, Object> root, GeneratorConfiguration configuration) throws Exception {
		Configuration cfg = getConfig();
		String suffix = "Mapper.xml";
		String template = "mybatis.ftl";
		String projectPath = configuration.getProjectPath();
		String javaFilePackage = getPackages(configuration);
		String domainObjectName = getDomainObjectName(configuration.getEntityName());
		String packagePath = javaFilePackage + "/" + domainObjectName + "/dao";
		writeSingleFile(cfg, root, template, projectPath, packagePath, configuration.getEntityName(), suffix,
				configuration.isOverwrite());
	}
	
	private static void mapperXmlFile(Map<String, Object> root, GeneratorConfiguration configuration) throws Exception {
		Configuration cfg = getConfig();
		String suffix = "Mapper.xml";
		String template = "mybatisMapper.ftl";
		String projectPath = configuration.getProjectPath();
		String javaFilePackage = getPackages(configuration);
		String domainObjectName = getDomainObjectName(configuration.getEntityName());
		String packagePath = javaFilePackage + "/" + domainObjectName + "/dao";
		writeSingleFile(cfg, root, template, projectPath, packagePath, configuration.getEntityName(), suffix,
				configuration.isDaowrite());
	}

	private static String getDomainObjectName(String domainObjectName) {
		return domainObjectName.toLowerCase();
	}

	/**
	 * @param cfg
	 * @param root
	 * @param template
	 * @param projectPath
	 * @param packagePath
	 * @param domainObjectName
	 * @param suffix
	 * @param overwrite
	 * @return
	 * @throws IOException
	 * @throws TemplateException
	 */
	public static boolean writeSingleFile(Configuration cfg, Map<String, Object> root, String template,
			String projectPath, String packagePath, String domainObjectName, String suffix, Boolean overwrite)
			throws IOException {

		Writer out = null;
		Template temp = cfg.getTemplate(template);
		File file = new File(String.format(sf, projectPath, packagePath, domainObjectName, suffix));
		try {
			if (overwrite) {
				if (!file.getParentFile().exists()) {
					if (!file.getParentFile().mkdirs()) {
						System.out.println("创建目标文件所在目录失败！");
						return false;
					}
				}
				if (!file.exists() && !overwrite) {
					file.createNewFile();
				}
				out = new FileWriter(file);
				temp.process(root, out);
				out.flush();
				System.out.println(String.format("创建单个文件：%s成功!", file.getPath()));
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(String.format("写入%s失败!", file.getPath()));
		} finally {
			if (out != null) {
				out.close();
			}
		}
		return true;
	}
}
