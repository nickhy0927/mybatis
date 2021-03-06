package com.mybatis.system.optlog.controller;

import com.mybatis.common.utils.MessageObject;
import com.mybatis.common.utils.PageSupport;
import com.mybatis.common.utils.PagerInfo;
import com.mybatis.common.utils.RequestData;
import com.mybatis.system.optlog.entity.OptLog;
import com.mybatis.system.optlog.service.OptLogService;
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
 * @Title: optLogController.java
 * @Package com.mybatis.system.optLog.controller
 * @Description TODO(用一句话描述该文件做什么)
 * @author yuanhuangd
 * @version V1.0
 * @Date: 2018年6月9日 下午6:03:46
 */
@Controller
public class OptLogController {

	@Autowired
	private OptLogService optLogService;

	/**
	 * 新增页面
	 */
	@RequestMapping(value = "/system/optlog/opt-log-create.do", method = RequestMethod.GET)
	public String optLogCreate() {
		return "module/system/optlog/opt-log-create";
	}

    /**
     * 查看日志详情
     * @return
     */
	@RequestMapping(value = "/system/optlog/opt-log-view/{id}.do", method = RequestMethod.GET)
	public String optLogView(@PathVariable(value = "id") String id, Model model) {
        OptLog optLog = optLogService.get(id);
        model.addAttribute("optLog", optLog);
        return "module/system/optlog/opt-log-view";
	}

	/**
	 * 新增数据到数据库
	 */
	@ResponseBody
    @RequestMapping(value = "/system/optlog/opt-log-save.json", method = RequestMethod.POST)
    public MessageObject optLogSave(OptLog optLog) {
        MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
        try {
            optLogService.insert(optLog);
            messageObject.ok("保存信息成功", optLog);
        } catch (Exception e) {
            messageObject.error("保存信息失败");
        }
        return messageObject;
    }
    
    /**
	 * 修改页面
	 */
	@RequestMapping(value = "/system/optlog/opt-log-edit/{id}.do", method = RequestMethod.GET)
	public String optLogEdit(@PathVariable(value = "id") String id, Model model) {
		OptLog optLog = optLogService.get(id);
		model.addAttribute("optLog", optLog);
		return "module/system/optlog/opt-log-edit";
	}
	
	/**
	 * 更新数据到数据库
	 */
	@ResponseBody
    @RequestMapping(value = "/system/optlog/opt-log-update.json", method = RequestMethod.POST)
    public MessageObject optLogupdate(OptLog optLog) {
        MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
        try {
            optLogService.update(optLog);
            messageObject.ok("修改信息成功", optLog);
        } catch (Exception e) {
            messageObject.error("修改信息失败");
        }
        return messageObject;
    }
	
	/**
	 * 从数据库删除数据
	 */
	@ResponseBody
	@RequestMapping(value = "/system/optlog/opt-log-delete/{id}.json", method = RequestMethod.POST)
	public MessageObject optLogDelete(@PathVariable(value = "id") String id) {
		MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
		try {
			String[] ids = id.split(",");
			List<String> list = Arrays.asList(ids);
			optLogService.deleteBatch(list);
			messageObject.ok("删除信息成功", null);
		} catch (Exception e) {
			messageObject.error("删除信息失败");
		}
		return messageObject;
	}

	/**
	 * 列表页面
	 */
	@RequestMapping(value = "/system/optlog/opt-log-list.do", method = RequestMethod.GET)
	public String optLogList() {
		return "module/system/optlog/opt-log-list";
	}

	/**
	 * 获取列表数据
	 */
    @ResponseBody
	@RequestMapping(value = "/system/optlog/opt-log-list.json", method = RequestMethod.POST)
	public MessageObject optLogList(HttpServletRequest request, PageSupport support) {
		MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
		try {
			Map<String, Object> paramsMap = RequestData.getRequestDataToMap(request);
			PagerInfo<OptLog> pagerInfo = optLogService.queryPageByMap(paramsMap, support);
			messageObject.ok("获取列表成功", pagerInfo);
		} catch (IOException e) {
			e.printStackTrace();
			messageObject.error("获取列表异常");
		}
		return messageObject;
	}
}
