package com.mybatis.deploy.category.controller;

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

import com.mybatis.deploy.category.entity.Category;
import com.mybatis.deploy.category.service.CategoryService;
import com.mybatis.common.utils.MessageObject;
import com.mybatis.common.utils.PageSupport;
import com.mybatis.common.utils.PagerInfo;
import com.mybatis.core.orm.entity.PageRowBounds;
import com.mybatis.common.utils.RequestData;

/**
 * @Title: categoryController.java
 * @Package com.mybatis.deploy.category.controller
 * @Description TODO(用一句话描述该文件做什么)
 * @author yuanhuangd
 * @version V1.0
 * @Date: 2018年6月9日 下午6:03:46
 */
@Controller
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	/**
	 * 新增页面
	 */
	@RequestMapping(value = "/deploy/category/category-create.do", method = RequestMethod.GET)
	public String categoryCreate() {
		return "module/deploy/category/category-create";
	}
	
	/**
	 * 新增数据到数据库
	 */
	@ResponseBody
    @RequestMapping(value = "/deploy/category/category-save.json", method = RequestMethod.POST)
    public MessageObject categorySave(Category category) {
        MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
        try {
            categoryService.insert(category);
            messageObject.ok("保存信息成功", category);
        } catch (Exception e) {
            messageObject.error("保存信息失败");
        }
        return messageObject;
    }
    
    /**
	 * 修改页面
	 */
	@RequestMapping(value = "/deploy/category/category-edit/{id}.do", method = RequestMethod.GET)
	public String categoryEdit(@PathVariable(value = "id") String id, Model model) {
		Category category = categoryService.get(id);
		model.addAttribute("category", category);
		return "module/deploy/category/category-edit";
	}
	
	/**
	 * 更新数据到数据库
	 */
	@ResponseBody
    @RequestMapping(value = "/deploy/category/category-update.json", method = RequestMethod.POST)
    public MessageObject categoryupdate(Category category) {
        MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
        try {
            categoryService.update(category);
            messageObject.ok("修改信息成功", category);
        } catch (Exception e) {
            messageObject.error("修改信息失败");
        }
        return messageObject;
    }
	
	/**
	 * 从数据库删除数据
	 */
	@ResponseBody
	@RequestMapping(value = "/deploy/category/category-delete/{id}.json", method = RequestMethod.POST)
	public MessageObject categoryDelete(@PathVariable(value = "id") String id) {
		MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
		try {
			String[] ids = id.split(",");
			List<String> list = Arrays.asList(ids);
			categoryService.deleteBatch(list);
			messageObject.ok("删除信息成功", null);
		} catch (Exception e) {
			messageObject.error("删除信息失败");
		}
		return messageObject;
	}

	/**
	 * 列表页面
	 */
	@RequestMapping(value = "/deploy/category/category-list.do", method = RequestMethod.GET)
	public String categoryList() {
		return "module/deploy/category/category-list";
	}

	/**
	 * 获取列表数据
	 */
    @ResponseBody
	@RequestMapping(value = "/deploy/category/category-list.json", method = RequestMethod.POST)
	public MessageObject categoryList(HttpServletRequest request, PageSupport support) {
		MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
		try {
			Map<String, Object> paramsMap = RequestData.getRequestDataToMap(request);
			PagerInfo<Category> pagerInfo = categoryService.queryPage(paramsMap, new PageRowBounds(support));
			messageObject.ok("获取列表成功", pagerInfo);
		} catch (IOException e) {
			e.printStackTrace();
			messageObject.error("获取列表异常");
		}
		return messageObject;
	}
}
