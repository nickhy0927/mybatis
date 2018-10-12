package com.mybatis.platform.icon.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mybatis.common.utils.MessageObject;
import com.mybatis.common.utils.PageSupport;
import com.mybatis.common.utils.PagerInfo;
import com.mybatis.common.utils.RequestData;
import com.mybatis.core.orm.controller.BaseController;
import com.mybatis.interceptor.Authority;
import com.mybatis.interceptor.OperateLog;
import com.mybatis.interceptor.OperateType;
import com.mybatis.platform.icon.entity.Icon;
import com.mybatis.platform.icon.service.IconService;

/**
 * @program: mybatis-base
 * @description: 图标控制层
 * @author: Mr.Huang
 * @create: 2018-08-22 12:41
 **/
@Controller
@Scope("prototype")
public class IconController extends BaseController{

    @Autowired
    private IconService iconService;

    /**
     * 新增页面
     */
    @Authority(alias = "icon-create")
    @RequestMapping(value = "/platform/icon/icon-create.do", method = RequestMethod.GET)
    public String iconCreate() {
        return "module/platform/icon/icon-create";
    }

    /**
     * 新增数据到数据库
     */
    @ResponseBody
    @RequestMapping(value = "/platform/icon/icon-save.json", method = RequestMethod.POST, produces = "application/json")
    @OperateLog(message = "新增图标信息", optType = OperateType.OptType.INSERT, service = IconService.class)
    public MessageObject iconSave(Icon icon) {
        MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
        try {
            iconService.insert(icon);
            messageObject.ok("图标信息保存成功", icon);
        } catch (Exception e) {
            messageObject.error("图标信息保存失败");
        } 
        return messageObject;
    }

    /**
     * 修改页面
     */
    @Authority(alias = "icon-edit")
    @RequestMapping(value = "/platform/icon/icon-edit/{id}.do", method = RequestMethod.GET)
    public String iconEdit(@PathVariable(value = "id") String id, Model model, HttpServletRequest request) {
        try {
            Icon icon = iconService.get(id);
            model.addAttribute("icon", icon);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "module/platform/icon/icon-edit";
    }

    /**
     * 更新数据到数据库
     */
    @ResponseBody
    @RequestMapping(value = "/platform/icon/icon-update.json", method = RequestMethod.POST, produces = "application/json")
    @OperateLog(message = "修改图标信息", optType = OperateType.OptType.UPDATE, service = IconService.class)
    public MessageObject iconupdate(Icon icon) {
        MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
        try {
            iconService.update(icon);
            messageObject.ok("修改图标信息成功", icon);
        } catch (Exception e) {
            e.printStackTrace();
            messageObject.error("修改图标信息失败");
        } 
        return messageObject;
    }

    /**
     * 从数据库删除数据
     */
    @ResponseBody
    @Authority(alias = "icon-delete")
    @RequestMapping(value = "/platform/icon/icon-delete/{id}.json", method = RequestMethod.POST)
    @OperateLog(message = "删除图标信息", optType = OperateType.OptType.DELETE, service = IconService.class)
    public MessageObject iconDelete(@PathVariable(value = "id") String id) {
        MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
        try {
            String[] ids = id.split(",");
            List<String> list = Arrays.asList(ids);
            iconService.deleteBatch(list);
            messageObject.ok("删除图标信息成功", null);
        } catch (Exception e) {
            messageObject.error("删除图标信息失败");
        }
        return messageObject;
    }

    /**
     * 列表页面
     */
    @Authority(alias = "icon-mgt")
    @RequestMapping(value = "/platform/icon/icon-list.do", method = RequestMethod.GET)
    public String iconList() {
        return "module/platform/icon/icon-list";
    }

    /**
     * 获取列表数据
     */
    @ResponseBody
    @RequestMapping(value = "/platform/icon/icon-list.json", method = RequestMethod.POST)
    public MessageObject iconList(HttpServletRequest request, PageSupport support) {
        MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
        try {
            Map<String, Object> paramsMap = RequestData.getRequestDataToMap(request);
            PagerInfo<Icon> pagerInfo = iconService.queryPageByMap(paramsMap, support);
            messageObject.ok("获取图标列表成功", pagerInfo);
        } catch (IOException e) {
            e.printStackTrace();
            messageObject.error("获取图标列表异常");
        } 
        return messageObject;
    }

}
