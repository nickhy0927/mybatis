package ${packages}.${domainObjectName?lower_case}.controller;

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

import ${packages}.${domainObjectName?lower_case}.entity.${domainObjectName};
import ${packages}.${domainObjectName?lower_case}.service.${domainObjectName}Service;
import com.mybatis.common.utils.MessageObject;
import com.mybatis.common.utils.PageSupport;
import com.mybatis.common.utils.PagerInfo;
import com.mybatis.common.utils.RequestData;

/**
 * @Title: ${domainObjectName?uncap_first}Controller.java
 * @Package ${packages}.${domainObjectName?uncap_first}.controller
 * @Description TODO(用一句话描述该文件做什么)
 * @author yuanhuangd
 * @version V1.0
 * @Date: 2018年6月9日 下午6:03:46
 */
@Controller
public class ${domainObjectName}Controller {

	@Autowired
	private ${domainObjectName}Service ${domainObjectName?uncap_first}Service;

	/**
	 * 新增页面
	 */
	@RequestMapping(value = "/${accessPath}-create.do", method = RequestMethod.GET)
	public String ${domainObjectName?uncap_first}Create() {
		return "${pagePath}-create";
	}
	
	/**
	 * 新增数据到数据库
	 */
	@ResponseBody
    @RequestMapping(value = "/${accessPath}-save.json", method = RequestMethod.POST)
    public MessageObject ${domainObjectName?uncap_first}Save(${domainObjectName} ${domainObjectName?uncap_first}) {
        MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
        try {
            ${domainObjectName?uncap_first} = ${domainObjectName?uncap_first}Service.insert(${domainObjectName?uncap_first});
            messageObject.ok("保存信息成功", ${domainObjectName?uncap_first});
        } catch (Exception e) {
            messageObject.error("保存信息失败");
        }
        return messageObject;
    }
    
    /**
	 * 修改页面
	 */
	@RequestMapping(value = "/${accessPath}-edit/{id}.do", method = RequestMethod.GET)
	public String ${domainObjectName?uncap_first}Edit(@PathVariable(value = "id") String id, Model model) {
		${domainObjectName} ${domainObjectName?uncap_first} = ${domainObjectName?uncap_first}Service.get(id);
		model.addAttribute("${domainObjectName?uncap_first}", ${domainObjectName?uncap_first});
		return "${pagePath}-edit";
	}
	
	/**
	 * 更新数据到数据库
	 */
	@ResponseBody
    @RequestMapping(value = "/${accessPath}-update.json", method = RequestMethod.POST)
    public MessageObject ${domainObjectName?uncap_first}update(${domainObjectName} ${domainObjectName?uncap_first}) {
        MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
        try {
            ${domainObjectName?uncap_first}Service.update(${domainObjectName?uncap_first});
            messageObject.ok("修改信息成功", ${domainObjectName?uncap_first});
        } catch (Exception e) {
            messageObject.error("修改信息失败");
        }
        return messageObject;
    }
	
	/**
	 * 从数据库删除数据
	 */
	@ResponseBody
	@RequestMapping(value = "/${accessPath}-delete/{id}.json", method = RequestMethod.POST)
	public MessageObject ${domainObjectName?uncap_first}Delete(@PathVariable(value = "id") String id) {
		MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
		try {
			String[] ids = id.split(",");
			List<String> list = Arrays.asList(ids);
			${domainObjectName?uncap_first}Service.deleteBatch(list);
			messageObject.ok("删除信息成功", null);
		} catch (Exception e) {
			messageObject.error("删除信息失败");
		}
		return messageObject;
	}

	/**
	 * 列表页面
	 */
	@RequestMapping(value = "/${accessPath}-list.do", method = RequestMethod.GET)
	public String ${domainObjectName?uncap_first}List() {
		return "${pagePath}-list";
	}

	/**
	 * 获取列表数据
	 */
	@RequestMapping(value = "/${accessPath}-list.json", method = RequestMethod.POST)
	public void ${domainObjectName?uncap_first}List(HttpServletRequest request, HttpServletResponse response, PageSupport support) {
		MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
		try {
			Map<String, Object> paramsMap = RequestData.getRequestDataToMap(request);
			PagerInfo<${domainObjectName}> pagerInfo = ${domainObjectName?uncap_first}Service.queryPageByMap(paramsMap, support);
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
