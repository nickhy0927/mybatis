package com.mybatis.config.template;

import com.alibaba.druid.util.StringUtils;
import com.mybatis.config.template.meta.TableColumn;
import com.mybatis.config.template.parser.GeneratorConfiguration;
import com.mybatis.config.template.util.Underline2Camel;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		String ftlPath = FileGenerator.class.getResource("/ftl").getPath();
		cfg.setDirectoryForTemplateLoading(new File(ftlPath)); // 需要文件夹绝对路径
		cfg.setDefaultEncoding("UTF-8");
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		return cfg;
	}

	public static void writeFile(GeneratorConfiguration configuration, List<TableColumn> columns, String domainObjectName) throws Exception {
		if (configuration.isOverwrite()) {
			Map<String, Object> root = new HashMap<>();
			root.put("domainObjectName", domainObjectName);
			root.put("config", configuration);
			root.put("tableName", configuration.getTableName());
			root.put("packages", configuration.getPackages());
			root.put("columnList", columns);
			domainFile(root, configuration);
			daoFile(root, configuration);
			daoImplFile(root, configuration);
			xmlFile(root, configuration);
			serviceFile(root, configuration);
			controllerFile(root, configuration);
			if (configuration.isList()) {
				listFile(root, configuration);
			}
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
		writeSingleFile(cfg, root, template, projectPath, packagePath, domainObjectName, suffix, configuration.isOverwrite());
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
		writeSingleFile(cfg, root, template, projectPath, packagePath, configuration.getEntityName(), suffix, configuration.isOverwrite());
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
		writeSingleFile(cfg, root, template, projectPath, packagePath, domainObjectName, suffix, configuration.isOverwrite());
	}

	private static void serviceFile(Map<String, Object> root, GeneratorConfiguration configuration) throws Exception {
		Configuration cfg = getConfig();
		String suffix = "Service.java";
		String projectPath = configuration.getProjectPath();
		String packagePath = getPackages(configuration) + "/" + getDomainObjectName(configuration.getEntityName()) + "/service";
		String template = "service.ftl";
		writeSingleFile(cfg, root, template, projectPath, packagePath, configuration.getEntityName(), suffix, configuration.isOverwrite());
	}
	
	private static void controllerFile(Map<String, Object> root, GeneratorConfiguration configuration) throws Exception {
		Configuration cfg = getConfig();
		String suffix = "Controller.java";
		String projectPath = configuration.getProjectPath();
		String packagePath = getPackages(configuration) + "/" + getDomainObjectName(configuration.getEntityName()) + "/controller";
		String template = "controller.ftl";
		String page = Underline2Camel.camel2Centerine(configuration.getEntityName()).toLowerCase();
		String pagePath = configuration.getTarget() + "/" + page;
		root.put("accessPath", configuration.getEntityName().toLowerCase() + "/" + page);
		if (StringUtils.isEmpty(configuration.getTarget())) {
			pagePath = configuration.getEntityName().toLowerCase() + "/" + page;
		}
		root.put("pagePath", pagePath);
		writeSingleFile(cfg, root, template, projectPath, packagePath, configuration.getEntityName(), suffix, configuration.isOverwrite());
	}

	private static void listFile(Map<String, Object> root, GeneratorConfiguration configuration) throws Exception {
		Configuration cfg = getConfig();
		String suffix = "-list.jsp";
		String projectPath = configuration.getBaseDir() + configuration.getTarget();
		String domainObjectName = Underline2Camel.camel2Centerine(configuration.getEntityName());
		String template = "list.ftl";
		root.put("ctx", "${pageContext.request.contextPath}");
		writeSingleFile(cfg, root, template, projectPath, "", domainObjectName.toLowerCase(), suffix, configuration.isOverwrite());
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
		writeSingleFile(cfg, root, template, projectPath, packagePath, configuration.getEntityName(), suffix, configuration.isOverwrite());
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
	public static boolean writeSingleFile(Configuration cfg, Map<String, Object> root, String template, String projectPath, String packagePath,
			String domainObjectName, String suffix, Boolean overwrite) throws IOException, TemplateException {

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
