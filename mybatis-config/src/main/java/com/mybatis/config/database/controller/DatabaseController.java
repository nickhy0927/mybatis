package com.mybatis.config.database.controller;

import java.io.File;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mybatis.common.utils.MessageObject;
import com.mybatis.common.utils.PageSupport;
import com.mybatis.common.utils.PagerInfo;
import com.mybatis.common.utils.RequestData;
import com.mybatis.config.database.entity.Database;
import com.mybatis.config.database.entity.TableComment;
import com.mybatis.config.database.service.DatabaseService;
import com.mybatis.config.template.parser.GeneratorConfiguration;
import com.mybatis.mysql.MySQLDatabaseBackup;

@Controller
@RequestMapping(value = "/config")
public class DatabaseController {

	@Autowired
	private DatabaseService databaseService;

	@RequestMapping(value = "/database/database-list.do", method = RequestMethod.GET)
	public ModelAndView databaseList() {
		ModelAndView view = new ModelAndView("module/config/database/database-list");
		return view;
	}

	@ResponseBody
	@RequestMapping(value = "/database/database-list.json", method = RequestMethod.POST)
	public MessageObject databaseList(HttpServletRequest request, PageSupport support) {
		MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
		try {
			Map<String, Object> dataToMap = RequestData.getRequestDataToMap(request);
			PagerInfo<Database> pagerInfo = databaseService.queryPageByMap(dataToMap, support);
			messageObject.ok("获取列表信息成功", pagerInfo);
		} catch (Exception e) {
			e.printStackTrace();
			messageObject.error("获取列表失败");
		}
		return messageObject;
	}

	@RequestMapping(value = "/database/database-create.do", method = RequestMethod.GET)
	public ModelAndView databaseCreate() {
		ModelAndView view = new ModelAndView("module/config/database/database-create");
		return view;
	}

	@ResponseBody
	@RequestMapping(value = "/database/database-save.json", method = RequestMethod.POST)
	public MessageObject databaseSave(Database database) {
		MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
		try {
			database = databaseService.insert(database);
			messageObject.ok("保存数据库信息成功", database);
		} catch (Exception e) {
			messageObject.error("保存数据库信息失败");
		}
		return messageObject;
	}

	@RequestMapping(value = "/database/database-detail-list.do", method = RequestMethod.GET)
	public ModelAndView databaseDetailList(String id) {
		ModelAndView view = new ModelAndView("module/config/database/database-detail-list");
		view.addObject("id", id);
		return view;
	}

	@ResponseBody
	@RequestMapping(value = "/database/getDatabaseDetailInfo.json")
	public MessageObject getDatabaseDetailInfo(String id, HttpServletRequest request, PageSupport support) {
		MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
		try {
			Database database = databaseService.get(id);
			Map<String, Object> dataToMap = RequestData.getRequestDataToMap(request);
			dataToMap.put("databaseName", database.getDatabaseName());
			PagerInfo<TableComment> pagerInfo = databaseService.queryTableNameAndCommentByPageMap(dataToMap, support);
			messageObject.ok("获取列表成功", pagerInfo);
		} catch (Exception e) {
			e.printStackTrace();
			messageObject.error("获取列表失败");
		}
		return messageObject;
	}

	@RequestMapping(value = "/database/{id}/create-template.do", method = RequestMethod.GET)
	public ModelAndView createTemplate(@PathVariable("id") String id, String tableName) {
		ModelAndView modelAndView = new ModelAndView("module/config/file/file");
		modelAndView.addObject("tableName", tableName);
		modelAndView.addObject("id", id);
		return modelAndView;
	}

	@RequestMapping(value = "/database/{id}/make-template.json", method = RequestMethod.POST)
	public @ResponseBody MessageObject makeTemplate(@PathVariable("id") String id, String tableName,
			GeneratorConfiguration configuration) {
		MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
		try {
			Database database = databaseService.get(id);
			String connectUrl = "jdbc:mysql://" + database.getIp() + ": " + database.getPort() + "/"
					+ database.getDatabaseName() + "?" + database.getParams();
			if (database.getDatabaseType() == 2) {

			}
			configuration.setConnectUrl(connectUrl);
			configuration.setDriverClassName(database.getDriverClassName());
			configuration.setUsername(database.getUsername());
			configuration.setPassword(database.getPassword());
			messageObject.ok("创建模板成功", configuration);
		} catch (Exception e) {
			messageObject.error("创建模板失败");
		}
		return messageObject;
	}

	@ResponseBody
	@RequestMapping(value = "/database/database-backup.json", method = RequestMethod.POST)
	public MessageObject backUpDatabase(String id) {
		MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
		System.out.println(System.getProperty("user.dir"));
		System.out.println(new File("/").getAbsolutePath());
		try {
			Database database = databaseService.get(id);
			String savePath = "src/main/resources/sql";
			boolean tool = MySQLDatabaseBackup.exportDatabaseTool(database, savePath);
			if (tool) {
				messageObject.ok("导出数据库成功", database);
			} else 
				messageObject.error("导出数据库失败");
		} catch (Exception e) {
			messageObject.error("导出数据库失败");
		}
		return messageObject;
	}
}
