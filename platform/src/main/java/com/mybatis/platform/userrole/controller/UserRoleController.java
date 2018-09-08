package com.mybatis.platform.userrole.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mybatis.common.utils.*;
import com.mybatis.core.orm.constant.SysConstant;
import com.mybatis.interceptor.Authority;
import com.mybatis.interceptor.MessageResources;
import com.mybatis.interceptor.OperateLog;
import com.mybatis.interceptor.OperateType;
import com.mybatis.platform.role.entity.Role;
import com.mybatis.platform.role.entity.RoleTree;
import com.mybatis.platform.role.service.RoleService;
import com.mybatis.platform.user.service.UserService;
import com.mybatis.platform.userrole.entity.UserRole;
import com.mybatis.platform.userrole.service.UserRoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.util.JAXBSource;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author yuanhuangd
 * @version V1.0
 * @Title: userRoleController.java
 * @Package com.mybatis.platform.userRole.controller
 * @Description 用户角色管理
 * @Date: 2018年6月9日 下午6:03:46
 */
@Controller
public class UserRoleController {

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    /**
     * 新增页面
     */
    @Authority(alias = "user-role-create")
    @RequestMapping(value = "/platform/userrole/user-role-create.do", method = RequestMethod.GET)
    public String userRoleCreate(ModelMap modelMap, String userId) {
        List<UserRole> userRoles = userRoleService.queryUserRoleList(userId);
        modelMap.put("defaultValue", JsonMapper.toJson(userRoles));
        modelMap.put("user", userService.get(userId));
        Map<String, Object> params = Maps.newConcurrentMap();
        params.put("status",  1);
        params.put("frozen", SysConstant.Frozen.YES);
        List<Role> roleList = roleService.queryListByMap(params);
        List<RoleTree> roleTrees = Lists.newArrayList();
        for(Role role : roleList) {
            roleTrees.add(new RoleTree(role));
        }
        modelMap.put("roleTrees", JsonMapper.toJson(roleTrees));
        return "module/platform/userrole/user-role-create";
    }

    /**
     * 新增数据到数据库
     */
    @ResponseBody
    @RequestMapping(value = "/platform/userrole/user-role-save.json", method = RequestMethod.POST)
    @OperateLog(message = "新增用户角色信息", optType = OperateType.OptType.INSERT, service = UserRoleService.class)
    public MessageObject userRoleSave(String userId, String roleIds) {
        MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
        try {
            List<UserRole> userRoles = Lists.newArrayList();
            if(StringUtils.isNotEmpty(roleIds)) {
                String[] strings = roleIds.split(",");
                for (String roleId: strings) {
                    UserRole userRole = new UserRole();
                    userRole.setRoleId(roleId);
                    userRole.setUserId(userId);
                    userRoles.add(userRole);
                }
                userRoleService.insertBatch(userRoles);
                messageObject.ok(MessageResources.getMessage("UserRole.save.success"), roleIds);
            } else messageObject.error(MessageResources.getMessage("UserRole.save.fail"));
        } catch (Exception e) {
            messageObject.error(MessageResources.getMessage("UserRole.save.fail"));
        }
        return messageObject;
    }

    /**
     * 修改页面
     */
    @Authority(alias = "user-role-edit")
    @RequestMapping(value = "/platform/userrole/user-role-edit/{id}.do", method = RequestMethod.GET)
    public String userRoleEdit(@PathVariable(value = "id") String id, Model model) {
        UserRole userRole = userRoleService.get(id);
        model.addAttribute("userRole", userRole);
        return "module/platform/userrole/user-role-edit";
    }

    /**
     * 更新数据到数据库
     */
    @ResponseBody
    @RequestMapping(value = "/platform/userrole/user-role-update.json", method = RequestMethod.POST)
    @OperateLog(message = "修改用户角色信息", optType = OperateType.OptType.UPDATE, service = UserRoleService.class)
    public MessageObject userRoleupdate(UserRole userRole) {
        MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
        try {
            userRoleService.update(userRole);
            messageObject.ok("修改信息成功", userRole);
        } catch (Exception e) {
            messageObject.error("修改信息失败");
        }
        return messageObject;
    }

    /**
     * 从数据库删除数据
     */
    @ResponseBody
    @Authority(alias = "user-role-delete")
    @RequestMapping(value = "/platform/userrole/user-role-delete/{id}.json", method = RequestMethod.POST)
    @OperateLog(message = "删除用户角色信息", optType = OperateType.OptType.DELETE, service = UserRoleService.class)
    public MessageObject userRoleDelete(@PathVariable(value = "id") String id) {
        MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
        try {
            String[] ids = id.split(",");
            List<String> list = Arrays.asList(ids);
            userRoleService.deleteBatch(list);
            messageObject.ok("删除信息成功", null);
        } catch (Exception e) {
            messageObject.error("删除信息失败");
        }
        return messageObject;
    }

    /**
     * 列表页面
     */
    @Authority(alias = "user-role-mgt")
    @RequestMapping(value = "/platform/userrole/user-role-list.do", method = RequestMethod.GET)
    public String userRoleList() {
        return "module/platform/userrole/user-role-list";
    }

    /**
     * 获取列表数据
     */
    @ResponseBody
    @RequestMapping(value = "/platform/userrole/user-role-list.json", method = RequestMethod.POST)
    public MessageObject userRoleList(HttpServletRequest request, PageSupport support) {
        MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
        try {
            Map<String, Object> paramsMap = RequestData.getRequestDataToMap(request);
            PagerInfo<UserRole> pagerInfo = userRoleService.queryPageByMap(paramsMap, support);
            messageObject.ok("获取模版输出成功", pagerInfo);
        } catch (IOException e) {
            e.printStackTrace();
            messageObject.error("获取模版数据异常");
        }
        return messageObject;
    }
}
