package com.mybatis.platform.basedata.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mybatis.platform.basedata.entity.BaseData;
import com.mybatis.platform.basedata.service.BaseDataService;
import com.mybatis.common.utils.MessageObject;
import com.mybatis.common.utils.PageSupport;
import com.mybatis.common.utils.PagerInfo;
import com.mybatis.common.utils.RequestData;

/**
 * @Title: baseDataController.java
 * @Package com.mybatis.platform.baseData.controller
 * @Description TODO(用一句话描述该文件做什么)
 * @author yuanhuangd
 * @version V1.0
 * @Date: 2018年6月9日 下午6:03:46
 */
@Controller
public class BaseDataController {

	@Autowired
	private BaseDataService baseDataService;

	/**
	 * 新增页面
	 */
	@RequestMapping(value = "/platform/basedata/base-data-create.do", method = RequestMethod.GET)
	public String baseDataCreate() {
		return "module/platform/basedata/base-data-create";
	}
	
	/**
	 * 新增数据到数据库
	 */
	@ResponseBody
    @RequestMapping(value = "/platform/basedata/base-data-save.json", method = RequestMethod.POST)
    public MessageObject baseDataSave(BaseData baseData) {
        MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
        try {
            baseData = baseDataService.insert(baseData);
            messageObject.ok("保存信息成功", baseData);
        } catch (Exception e) {
            messageObject.error("保存信息失败");
        }
        return messageObject;
    }
    
    /**
	 * 修改页面
	 */
	@RequestMapping(value = "/platform/basedata/base-data-edit/{id}.do", method = RequestMethod.GET)
	public String baseDataEdit(@PathVariable(value = "id") String id, Model model) {
		BaseData baseData = baseDataService.get(id);
		model.addAttribute("baseData", baseData);
		return "module/platform/basedata/base-data-edit";
	}
	
	/**
	 * 更新数据到数据库
	 */
	@ResponseBody
    @RequestMapping(value = "/platform/basedata/base-data-update.json", method = RequestMethod.POST)
    public MessageObject baseDataupdate(BaseData baseData) {
        MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
        try {
            baseDataService.update(baseData);
            messageObject.ok("修改信息成功", baseData);
        } catch (Exception e) {
            messageObject.error("修改信息失败");
        }
        return messageObject;
    }
	
	/**
	 * 从数据库删除数据
	 */
	@ResponseBody
	@RequestMapping(value = "/platform/basedata/base-data-delete/{id}.json", method = RequestMethod.POST)
	public MessageObject baseDataDelete(@PathVariable(value = "id") String id) {
		MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
		try {
			String[] ids = id.split(",");
			List<String> list = Arrays.asList(ids);
			baseDataService.deleteBatch(list);
			messageObject.ok("删除信息成功", null);
		} catch (Exception e) {
			messageObject.error("删除信息失败");
		}
		return messageObject;
	}

	/**
	 * 列表页面
	 */
	@RequestMapping(value = "/platform/basedata/base-data-list.do", method = RequestMethod.GET)
	public String baseDataList() {
		return "module/platform/basedata/base-data-list";
	}

	/**
	 * 获取列表数据
	 */
	@RequestMapping(value = "/platform/basedata/base-data-list.json", method = RequestMethod.POST)
	public void baseDataList(HttpServletRequest request, HttpServletResponse response, PageSupport support) {
		MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
		try {
			Map<String, Object> paramsMap = RequestData.getRequestDataToMap(request);
			PagerInfo<BaseData> pagerInfo = baseDataService.queryPageByMap(paramsMap, support);
			messageObject.ok("获取模版输出成功", pagerInfo);
		} catch (IOException e) {
			e.printStackTrace();
			messageObject.error("获取模版数据异常");
		} finally {
			try {
				messageObject.returnData(response, messageObject);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
