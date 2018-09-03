package com.mybatis.platform.attachment.controller;

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

import com.mybatis.platform.attachment.entity.Attachment;
import com.mybatis.platform.attachment.service.AttachmentService;
import com.mybatis.common.utils.MessageObject;
import com.mybatis.common.utils.PageSupport;
import com.mybatis.common.utils.PagerInfo;
import com.mybatis.core.orm.entity.PageRowBounds;
import com.mybatis.common.utils.RequestData;

/**
 * @Title: attachmentController.java
 * @Package com.mybatis.platform.attachment.controller
 * @Description TODO(用一句话描述该文件做什么)
 * @author yuanhuangd
 * @version V1.0
 * @Date: 2018年6月9日 下午6:03:46
 */
@Controller
public class AttachmentController {

	@Autowired
	private AttachmentService attachmentService;

	/**
	 * 新增页面
	 */
	@RequestMapping(value = "/platform/attachment/attachment-create.do", method = RequestMethod.GET)
	public String attachmentCreate() {
		return "module/platform/attachment/attachment-create";
	}
	
	/**
	 * 新增数据到数据库
	 */
	@ResponseBody
    @RequestMapping(value = "/platform/attachment/attachment-save.json", method = RequestMethod.POST)
    public MessageObject attachmentSave(Attachment attachment) {
        MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
        try {
            attachmentService.insert(attachment);
            messageObject.ok("保存信息成功", attachment);
        } catch (Exception e) {
            messageObject.error("保存信息失败");
        }
        return messageObject;
    }
    
    /**
	 * 修改页面
	 */
	@RequestMapping(value = "/platform/attachment/attachment-edit/{id}.do", method = RequestMethod.GET)
	public String attachmentEdit(@PathVariable(value = "id") String id, Model model) {
		Attachment attachment = attachmentService.get(id);
		model.addAttribute("attachment", attachment);
		return "module/platform/attachment/attachment-edit";
	}
	
	/**
	 * 更新数据到数据库
	 */
	@ResponseBody
    @RequestMapping(value = "/platform/attachment/attachment-update.json", method = RequestMethod.POST)
    public MessageObject attachmentupdate(Attachment attachment) {
        MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
        try {
            attachmentService.update(attachment);
            messageObject.ok("修改信息成功", attachment);
        } catch (Exception e) {
            messageObject.error("修改信息失败");
        }
        return messageObject;
    }
	
	/**
	 * 从数据库删除数据
	 */
	@ResponseBody
	@RequestMapping(value = "/platform/attachment/attachment-delete/{id}.json", method = RequestMethod.POST)
	public MessageObject attachmentDelete(@PathVariable(value = "id") String id) {
		MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
		try {
			String[] ids = id.split(",");
			List<String> list = Arrays.asList(ids);
			attachmentService.deleteBatch(list);
			messageObject.ok("删除信息成功", null);
		} catch (Exception e) {
			messageObject.error("删除信息失败");
		}
		return messageObject;
	}

	/**
	 * 列表页面
	 */
	@RequestMapping(value = "/platform/attachment/attachment-list.do", method = RequestMethod.GET)
	public String attachmentList() {
		return "module/platform/attachment/attachment-list";
	}

	/**
	 * 获取列表数据
	 */
    @ResponseBody
	@RequestMapping(value = "/platform/attachment/attachment-list.json", method = RequestMethod.POST)
	public MessageObject attachmentList(HttpServletRequest request, PageSupport support) {
		MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
		try {
			Map<String, Object> paramsMap = RequestData.getRequestDataToMap(request);
			PagerInfo<Attachment> pagerInfo = attachmentService.queryPage(paramsMap, new PageRowBounds(support));
			messageObject.ok("获取列表成功", pagerInfo);
		} catch (IOException e) {
			e.printStackTrace();
			messageObject.error("获取列表异常");
		}
		return messageObject;
	}
}
