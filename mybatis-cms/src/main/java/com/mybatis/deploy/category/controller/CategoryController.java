package com.mybatis.deploy.category.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mybatis.common.utils.*;
import com.mybatis.core.orm.constant.SysConstant;
import com.mybatis.deploy.category.entity.Category;
import com.mybatis.deploy.category.entity.CategoryTree;
import com.mybatis.deploy.category.service.CategoryService;
import com.mybatis.interceptor.Authority;
import com.mybatis.interceptor.OperateLog;
import com.mybatis.interceptor.OperateType;
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
 * @Title: categoryController.java
 * @Package com.mybatis.deploy.category.controller
 * @Description 栏目管理
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
	@Authority(alias = "categroy-create")
	@RequestMapping(value = "/deploy/category/category-create.do", method = RequestMethod.GET)
	public String categoryCreate(Model model) {
	    model.addAttribute("code", NumberCreate.generateNumber2());
        Map<String, Object> paramsMap = Maps.newConcurrentMap();
        paramsMap.put("status", SysConstant.DataStatus.VALID);
        List<Category> categoryList = categoryService.queryListByMap(paramsMap);
        List<CategoryTree> categoryTrees = Lists.newArrayList();
        for (Category category : categoryList) {
            categoryTrees.add(new CategoryTree(category));
        }
        model.addAttribute("catgoryList", JsonMapper.toJson(categoryTrees));
		return "module/deploy/category/category-create";
	}
	
	/**
	 * 新增数据到数据库
	 */
	@ResponseBody
    @OperateLog(message = "新增栏目", optType = OperateType.OptType.INSERT, service = CategoryService.class)
	@RequestMapping(value = "/deploy/category/category-save.json", method = RequestMethod.POST)
    public MessageObject categorySave(Category category) {
        MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
        try {
            categoryService.insert(category);
            messageObject.ok("保存栏目信息成功", category);
        } catch (Exception e) {
            e.printStackTrace();
            messageObject.error("保存栏目信息失败");
        }
        return messageObject;
    }
    
    /**
	 * 修改页面
	 */
    @Authority(alias = "category-edit")
	@RequestMapping(value = "/deploy/category/category-edit/{id}.do", method = RequestMethod.GET)
	public String categoryEdit(@PathVariable(value = "id") String id, Model model) {
        Map<String, Object> paramsMap = Maps.newConcurrentMap();
        paramsMap.put("status", SysConstant.DataStatus.VALID);
        List<Category> categoryList = categoryService.queryListByMap(paramsMap);
        List<CategoryTree> categoryTrees = Lists.newArrayList();
        for (Category category : categoryList) {
            categoryTrees.add(new CategoryTree(category));
        }
        model.addAttribute("catgoryList", JsonMapper.toJson(categoryTrees));
        Category category = categoryService.get(id);
        model.addAttribute("category", category);
		return "module/deploy/category/category-edit";
	}
	
	/**
	 * 更新数据到数据库
	 */
	@ResponseBody
	@OperateLog(message = "修改栏目", optType = OperateType.OptType.UPDATE, service = CategoryService.class)
    @RequestMapping(value = "/deploy/category/category-update.json", method = RequestMethod.POST)
    public MessageObject categoryupdate(Category category) {
        MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
        try {
            categoryService.update(category);
            messageObject.ok("修改栏目信息成功", category);
        } catch (Exception e) {
            messageObject.error("修改栏目信息失败");
        }
        return messageObject;
    }
	
	/**
	 * 从数据库删除数据
	 */
	@ResponseBody
	@Authority(alias = "categroy-delete")
	@RequestMapping(value = "/deploy/category/category-delete/{id}.json", method = RequestMethod.POST)
	public MessageObject categoryDelete(@PathVariable(value = "id") String id) {
		MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
		try {
			String[] ids = id.split(",");
			List<String> list = Arrays.asList(ids);
			categoryService.deleteBatch(list);
			messageObject.ok("删除栏目信息成功", null);
		} catch (Exception e) {
		    e.printStackTrace();
			messageObject.error("删除栏目信息失败");
		}
		return messageObject;
	}

	/**
	 * 列表页面
	 */
    @Authority(alias = "category-mgt")
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
			PagerInfo<Category> pagerInfo = categoryService.queryPageByMap(paramsMap, support);
			messageObject.ok("获取栏目列表成功", pagerInfo);
		} catch (IOException e) {
			e.printStackTrace();
			messageObject.error("获取栏目列表异常");
		}
		return messageObject;
	}
}
