package com.mybatis.deploy.picture.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.mybatis.interceptor.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mybatis.deploy.picture.entity.Picture;
import com.mybatis.deploy.picture.service.PictureService;
import com.mybatis.common.utils.MessageObject;
import com.mybatis.common.utils.PageSupport;
import com.mybatis.common.utils.PagerInfo;
import com.mybatis.core.orm.entity.PageRowBounds;
import com.mybatis.common.utils.RequestData;

/**
 * @Title: pictureController.java
 * @Package com.mybatis.deploy.picture.controller
 * @Description TODO(用一句话描述该文件做什么)
 * @author yuanhuangd
 * @version V1.0
 * @Date: 2018年6月9日 下午6:03:46
 */
@Controller
public class PictureController {

	@Autowired
	private PictureService pictureService;

	/**
	 * 新增页面
	 */
    @Authority(alias = "picture-create")
	@RequestMapping(value = "/deploy/picture/picture-create.do", method = RequestMethod.GET)
	public String pictureCreate() {
		return "module/deploy/picture/picture-create";
	}
	
	/**
	 * 新增数据到数据库
	 */
	@ResponseBody
    @RequestMapping(value = "/deploy/picture/picture-save.json", method = RequestMethod.POST)
    public MessageObject pictureSave(Picture picture) {
        MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
        try {
            pictureService.insert(picture);
            messageObject.ok("保存信息成功", picture);
        } catch (Exception e) {
            messageObject.error("保存信息失败");
        }
        return messageObject;
    }
    
    /**
	 * 修改页面
	 */
    @Authority(alias = "picture-edit")
	@RequestMapping(value = "/deploy/picture/picture-edit/{id}.do", method = RequestMethod.GET)
	public String pictureEdit(@PathVariable(value = "id") String id, Model model) {
		Picture picture = pictureService.get(id);
		model.addAttribute("picture", picture);
		return "module/deploy/picture/picture-edit";
	}
	
	/**
	 * 更新数据到数据库
	 */
	@ResponseBody
    @RequestMapping(value = "/deploy/picture/picture-update.json", method = RequestMethod.POST)
    public MessageObject pictureupdate(Picture picture) {
        MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
        try {
            pictureService.update(picture);
            messageObject.ok("修改信息成功", picture);
        } catch (Exception e) {
            messageObject.error("修改信息失败");
        }
        return messageObject;
    }
	
	/**
	 * 从数据库删除数据
	 */
	@ResponseBody
    @Authority(alias = "picture-delete")
	@RequestMapping(value = "/deploy/picture/picture-delete/{id}.json", method = RequestMethod.POST)
	public MessageObject pictureDelete(@PathVariable(value = "id") String id) {
		MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
		try {
			String[] ids = id.split(",");
			List<String> list = Arrays.asList(ids);
			pictureService.deleteBatch(list);
			messageObject.ok("删除信息成功", null);
		} catch (Exception e) {
			messageObject.error("删除信息失败");
		}
		return messageObject;
	}

	/**
	 * 列表页面
	 */
	@Authority(alias = "picture-mgt")
	@RequestMapping(value = "/deploy/picture/picture-list.do", method = RequestMethod.GET)
	public String pictureList() {
		return "module/deploy/picture/picture-list";
	}

	/**
	 * 获取列表数据
	 */
    @ResponseBody
	@RequestMapping(value = "/deploy/picture/picture-list.json", method = RequestMethod.POST)
	public MessageObject pictureList(HttpServletRequest request, PageSupport support) {
		MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
		try {
			Map<String, Object> paramsMap = RequestData.getRequestDataToMap(request);
			PagerInfo<Picture> pagerInfo = pictureService.queryPage(paramsMap, new PageRowBounds(support));
			messageObject.ok("获取列表成功", pagerInfo);
		} catch (IOException e) {
			e.printStackTrace();
			messageObject.error("获取列表异常");
		}
		return messageObject;
	}
}
