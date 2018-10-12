package com.mybatis.deploy.poster.controller;

import com.mybatis.common.utils.MessageObject;
import com.mybatis.common.utils.PageSupport;
import com.mybatis.common.utils.PagerInfo;
import com.mybatis.common.utils.RequestData;
import com.mybatis.deploy.poster.entity.Poster;
import com.mybatis.deploy.poster.service.PosterService;
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
 * @Title: posterController.java
 * @Package com.mybatis.deploy.poster.controller
 * @Description TODO(用一句话描述该文件做什么)
 * @author yuanhuangd
 * @version V1.0
 * @Date: 2018年6月9日 下午6:03:46
 */
@Controller
public class PosterController {

	@Autowired
	private PosterService posterService;

	/**
	 * 新增页面
	 */
	@RequestMapping(value = "/deploy/poster/poster-create.do", method = RequestMethod.GET)
	public String posterCreate() {
		return "module/deploy/poster/poster-create";
	}
	
	/**
	 * 新增数据到数据库
	 */
	@ResponseBody
    @RequestMapping(value = "/deploy/poster/poster-save.json", method = RequestMethod.POST)
    public MessageObject posterSave(Poster poster) {
        MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
        try {
            posterService.insert(poster);
            messageObject.ok("保存信息成功", poster);
        } catch (Exception e) {
            messageObject.error("保存信息失败");
        }
        return messageObject;
    }
    
    /**
	 * 修改页面
	 */
	@RequestMapping(value = "/deploy/poster/poster-edit/{id}.do", method = RequestMethod.GET)
	public String posterEdit(@PathVariable(value = "id") String id, Model model) {
		Poster poster = posterService.get(id);
		model.addAttribute("poster", poster);
		return "module/deploy/poster/poster-edit";
	}
	
	/**
	 * 更新数据到数据库
	 */
	@ResponseBody
    @RequestMapping(value = "/deploy/poster/poster-update.json", method = RequestMethod.POST)
    public MessageObject posterupdate(Poster poster) {
        MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
        try {
            posterService.update(poster);
            messageObject.ok("修改信息成功", poster);
        } catch (Exception e) {
            messageObject.error("修改信息失败");
        }
        return messageObject;
    }
	
	/**
	 * 从数据库删除数据
	 */
	@ResponseBody
	@RequestMapping(value = "/deploy/poster/poster-delete/{id}.json", method = RequestMethod.POST)
	public MessageObject posterDelete(@PathVariable(value = "id") String id) {
		MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
		try {
			String[] ids = id.split(",");
			List<String> list = Arrays.asList(ids);
			posterService.deleteBatch(list);
			messageObject.ok("删除信息成功", null);
		} catch (Exception e) {
			messageObject.error("删除信息失败");
		}
		return messageObject;
	}

	/**
	 * 列表页面
	 */
	@RequestMapping(value = "/deploy/poster/poster-list.do", method = RequestMethod.GET)
	public String posterList() {
		return "module/deploy/poster/poster-list";
	}

	/**
	 * 获取列表数据
	 */
    @ResponseBody
	@RequestMapping(value = "/deploy/poster/poster-list.json", method = RequestMethod.POST)
	public MessageObject posterList(HttpServletRequest request, PageSupport support) {
		MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
		try {
			Map<String, Object> paramsMap = RequestData.getRequestDataToMap(request);
			PagerInfo<Poster> pagerInfo = posterService.queryPageByMap(paramsMap, support);
			messageObject.ok("获取列表成功", pagerInfo);
		} catch (IOException e) {
			e.printStackTrace();
			messageObject.error("获取列表异常");
		}
		return messageObject;
	}
}
