package com.mybatis.system.exceptionlog.controller;

import com.mybatis.common.utils.MessageObject;
import com.mybatis.common.utils.PageSupport;
import com.mybatis.common.utils.PagerInfo;
import com.mybatis.common.utils.RequestData;
import com.mybatis.system.exceptionlog.entity.ExceptionLog;
import com.mybatis.system.exceptionlog.service.ExceptionLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @Title: exceptionLogController.java
 * @Package com.mybatis.system.exceptionLog.controller
 * @Description 系统异常
 * @author yuanhuangd
 * @version V1.0
 * @Date: 2018年6月9日 下午6:03:46
 */
@Controller
public class ExceptionLogController {

	@Autowired
	private ExceptionLogService exceptionLogService;

	/**
	 * 新增页面
	 */
	@RequestMapping(value = "/system/exceptionlog/exception-log-create.do", method = RequestMethod.GET)
	public String exceptionLogCreate() {
		return "module/system/exceptionlog/exception-log-create";
	}
	
	/**
	 * 新增数据到数据库
	 */
	@ResponseBody
    @RequestMapping(value = "/system/exceptionlog/exception-log-save.json", method = RequestMethod.POST)
    public MessageObject exceptionLogSave(ExceptionLog exceptionLog) {
        MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
        try {
            exceptionLogService.insert(exceptionLog);
            messageObject.ok("保存信息成功", exceptionLog);
        } catch (Exception e) {
            messageObject.error("保存信息失败");
        }
        return messageObject;
    }
    
    /**
	 * 修改页面
	 */
	@RequestMapping(value = "/system/exceptionlog/exception-log-edit/{id}.do", method = RequestMethod.GET)
	public String exceptionLogEdit(@PathVariable(value = "id") String id, Model model) {
		ExceptionLog exceptionLog = exceptionLogService.get(id);
		model.addAttribute("exceptionLog", exceptionLog);
		return "module/system/exceptionlog/exception-log-edit";
	}
	
	/**
	 * 更新数据到数据库
	 */
	@ResponseBody
    @RequestMapping(value = "/system/exceptionlog/exception-log-update.json", method = RequestMethod.POST)
    public MessageObject exceptionLogupdate(ExceptionLog exceptionLog) {
        MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
        try {
            exceptionLogService.update(exceptionLog);
            messageObject.ok("修改信息成功", exceptionLog);
        } catch (Exception e) {
            messageObject.error("修改信息失败");
        }
        return messageObject;
    }
	
	/**
	 * 从数据库删除数据
	 */
	@ResponseBody
	@RequestMapping(value = "/system/exceptionlog/exception-log-delete/{id}.json", method = RequestMethod.POST)
	public MessageObject exceptionLogDelete(@PathVariable(value = "id") String id) {
		MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
		try {
			String[] ids = id.split(",");
			List<String> list = Arrays.asList(ids);
			exceptionLogService.deleteBatch(list);
			messageObject.ok("删除信息成功", null);
		} catch (Exception e) {
			messageObject.error("删除信息失败");
		}
		return messageObject;
	}

	/**
	 * 列表页面
	 */
	@RequestMapping(value = "/system/exceptionlog/exception-log-list.do", method = RequestMethod.GET)
	public String exceptionLogList() {
		return "module/system/exceptionlog/exception-log-list";
	}

	/**
	 * 获取列表数据
	 */
    @ResponseBody
	@RequestMapping(value = "/system/exceptionlog/exception-log-list.json", method = RequestMethod.POST)
	public MessageObject exceptionLogList(HttpServletRequest request, PageSupport support) {
		MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
		try {
			Map<String, Object> paramsMap = RequestData.getRequestDataToMap(request);
			PagerInfo<ExceptionLog> pagerInfo = exceptionLogService.queryPageByMap(paramsMap, support);
			messageObject.ok("获取列表成功", pagerInfo);
		} catch (IOException e) {
			e.printStackTrace();
			messageObject.error("获取列表异常");
		}
		return messageObject;
	}
}
