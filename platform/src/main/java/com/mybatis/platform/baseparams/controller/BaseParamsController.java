package com.mybatis.platform.baseparams.controller;

import com.mybatis.common.utils.MessageObject;
import com.mybatis.common.utils.PageSupport;
import com.mybatis.common.utils.PagerInfo;
import com.mybatis.common.utils.RequestData;
import com.mybatis.core.orm.entity.PageRowBounds;
import com.mybatis.platform.baseparams.entity.BaseParams;
import com.mybatis.platform.baseparams.service.BaseParamsService;
import com.mybatis.utils.NumberCreate;
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
 * @Title: baseParamsController.java
 * @Package com.mybatis.platform.baseParams.controller
 * @Description 系统参数列表
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
	public String baseParamsCreate(Model model) {
		model.addAttribute("code", NumberCreate.generateNumber2());
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
            baseParamsService.insert(baseParams);
            messageObject.ok("保存系统参数信息成功", baseParams);
        } catch (Exception e) {
            messageObject.error("保存系统参数信息失败");
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
            messageObject.ok("修改系统参数信息成功", baseParams);
        } catch (Exception e) {
            messageObject.error("修改系统参数信息失败");
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
			String[] ids = id.split(",");
			List<String> list = Arrays.asList(ids);
			baseParamsService.deleteBatch(list);
			messageObject.ok("删除系统参数信息成功", null);
		} catch (Exception e) {
			messageObject.error("删除系统参数信息失败");
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
	public void baseParamsList(HttpServletRequest request, PageSupport support) {
		MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
		try {
			Map<String, Object> paramsMap = RequestData.getRequestDataToMap(request);
			PagerInfo<BaseParams> pagerInfo = baseParamsService.queryPage(paramsMap, new PageRowBounds(support));
			messageObject.ok("获取系统参数列表成功", pagerInfo);
		} catch (IOException e) {
			e.printStackTrace();
			messageObject.error("获取系统参数列表失败");
		} finally {
			try {
				messageObject.returnData(messageObject);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
