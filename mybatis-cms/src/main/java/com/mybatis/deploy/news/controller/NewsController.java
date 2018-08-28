package com.mybatis.deploy.news.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mybatis.deploy.news.entity.News;
import com.mybatis.deploy.news.service.NewsService;
import com.mybatis.common.utils.MessageObject;
import com.mybatis.common.utils.PageSupport;
import com.mybatis.common.utils.PagerInfo;
import com.mybatis.core.orm.entity.PageRowBounds;
import com.mybatis.common.utils.RequestData;

/**
 * @Title: newsController.java
 * @Package com.mybatis.deploy.news.controller
 * @Description TODO(用一句话描述该文件做什么)
 * @author yuanhuangd
 * @version V1.0
 * @Date: 2018年6月9日 下午6:03:46
 */
@Controller
public class NewsController {

	@Autowired
	private NewsService newsService;

	/**
	 * 新增页面
	 */
	@RequestMapping(value = "/deploy/news/news-create.do", method = RequestMethod.GET)
	public String newsCreate() {
		return "module/deploy/news/news-create";
	}
	
	/**
	 * 新增数据到数据库
	 */
	@ResponseBody
    @RequestMapping(value = "/deploy/news/news-save.json", method = RequestMethod.POST)
    public MessageObject newsSave(News news) {
        MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
        try {
            newsService.insert(news);
            messageObject.ok("保存信息成功", news);
        } catch (Exception e) {
            messageObject.error("保存信息失败");
        }
        return messageObject;
    }
    
    /**
	 * 修改页面
	 */
	@RequestMapping(value = "/deploy/news/news-edit/{id}.do", method = RequestMethod.GET)
	public String newsEdit(@PathVariable(value = "id") String id, Model model) {
		News news = newsService.get(id);
		model.addAttribute("news", news);
		return "module/deploy/news/news-edit";
	}
	
	/**
	 * 更新数据到数据库
	 */
	@ResponseBody
    @RequestMapping(value = "/deploy/news/news-update.json", method = RequestMethod.POST)
    public MessageObject newsupdate(News news) {
        MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
        try {
            newsService.update(news);
            messageObject.ok("修改信息成功", news);
        } catch (Exception e) {
            messageObject.error("修改信息失败");
        }
        return messageObject;
    }
	
	/**
	 * 从数据库删除数据
	 */
	@ResponseBody
	@RequestMapping(value = "/deploy/news/news-delete/{id}.json", method = RequestMethod.POST)
	public MessageObject newsDelete(@PathVariable(value = "id") String id) {
		MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
		try {
			String[] ids = id.split(",");
			List<String> list = Arrays.asList(ids);
			newsService.deleteBatch(list);
			messageObject.ok("删除信息成功", null);
		} catch (Exception e) {
			messageObject.error("删除信息失败");
		}
		return messageObject;
	}

	/**
	 * 列表页面
	 */
	@RequestMapping(value = "/deploy/news/news-list.do", method = RequestMethod.GET)
	public String newsList() {
		return "module/deploy/news/news-list";
	}

	/**
	 * 获取列表数据
	 */
    @ResponseBody
	@RequestMapping(value = "/deploy/news/news-list.json", method = RequestMethod.POST)
	public MessageObject newsList(HttpServletRequest request, PageSupport support) {
		MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
		try {
			Map<String, Object> paramsMap = RequestData.getRequestDataToMap(request);
			PagerInfo<News> pagerInfo = newsService.queryPage(paramsMap, new PageRowBounds(support));
			messageObject.ok("获取列表成功", pagerInfo);
		} catch (IOException e) {
			e.printStackTrace();
			messageObject.error("获取列表异常");
		}
		return messageObject;
	}
}
