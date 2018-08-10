package com.mybatis.platform.baseparams.controller;

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

import com.mybatis.platform.baseparams.entity.BaseParams;
import com.mybatis.platform.baseparams.service.BaseParamsService;
import com.mybatis.common.utils.MessageObject;
import com.mybatis.common.utils.PageSupport;
import com.mybatis.common.utils.PagerInfo;
import com.mybatis.common.utils.RequestData;

/**
 * @Title: baseParamsController.java
 * @Package com.mybatis.platform.baseParams.controller
 * @Description 系统参数配置
 * @author yuanhuangd
 * @version V1.0
 * @Date: 2018年6月9日 下午6:03:46
 */
@Controller
public class BaseParamsController {

	@Autowired
	private BaseParamsService baseParamsService;

	/**
	 * 新增页面
	 */
	@RequestMapping(value = "/platform/baseparams/base-params-create.do", method = RequestMethod.GET)
	public String baseParamsCreate() {
		return "module/platform/baseparams/base-params-create";
	}
	
	/**
	 * 新增数据到数据库
	 */
	@ResponseBody
    @RequestMapping(value = "/platform/baseparams/base-params-save.json", method = RequestMethod.POST)
    public MessageObject baseParamsSave(BaseParams baseParams) {
        MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
        try {
            baseParams = baseParamsService.insert(baseParams);
            messageObject.ok("保存信息成功", baseParams);
        } catch (Exception e) {
            messageObject.error("保存信息失败");
        }
        return messageObject;
    }
    
    /**
	 * 修改页面
	 */
	@RequestMapping(value = "/platform/baseparams/base-params-edit/{id}.do", method = RequestMethod.GET)
	public String baseParamsEdit(@PathVariable(value = "id") String id, Model model) {
		BaseParams baseParams = baseParamsService.get(id);
		model.addAttribute("baseParams", baseParams);
		return "module/platform/baseparams/base-params-edit";
	}
	
	/**
	 * 更新数据到数据库
	 */
	@ResponseBody
    @RequestMapping(value = "/platform/baseparams/base-params-update.json", method = RequestMethod.POST)
    public MessageObject baseParamsupdate(BaseParams baseParams) {
        MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
        try {
            baseParamsService.update(baseParams);
            messageObject.ok("修改信息成功", baseParams);
        } catch (Exception e) {
            messageObject.error("修改信息失败");
        }
        return messageObject;
    }
	
	/**
	 * 从数据库删除数据
	 */
	@ResponseBody
	@RequestMapping(value = "/platform/baseparams/base-params-delete/{id}.json", method = RequestMethod.POST)
	public MessageObject baseParamsDelete(@PathVariable(value = "id") String id) {
		MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
		try {
			List<String> list = Arrays.asList(id.split(","));
			baseParamsService.deleteBatch(list);
			messageObject.ok("删除信息成功", null);
		} catch (Exception e) {
			messageObject.error("删除信息失败");
		}
		return messageObject;
	}

	/**
	 * 列表页面
	 */
	@RequestMapping(value = "/platform/baseparams/base-params-list.do", method = RequestMethod.GET)
	public String baseParamsList() {
		return "module/platform/baseparams/base-params-list";
	}

	/**
	 * 获取列表数据
	 */
	@RequestMapping(value = "/platform/baseparams/base-params-list.json", method = RequestMethod.POST)
	public void baseParamsList(HttpServletRequest request, HttpServletResponse response, PageSupport support) {
		MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
		try {
			Map<String, Object> paramsMap = RequestData.getRequestDataToMap(request);
			PagerInfo<BaseParams> pagerInfo = baseParamsService.queryPageByMap(paramsMap, support);
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
