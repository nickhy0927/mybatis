package com.mybatis.utils;

import com.google.common.collect.Maps;
import com.mybatis.common.utils.BeanCopyUtils;
import com.mybatis.common.utils.JedisUtil;
import com.mybatis.common.utils.JsonMapper;
import com.mybatis.core.orm.constant.SysConstant;
import com.mybatis.platform.menu.entity.Menu;
import com.mybatis.platform.menu.service.MenuService;
import com.mybatis.platform.role.entity.Role;
import com.mybatis.platform.role.service.RoleService;
import com.mybatis.platform.user.entity.User;
import com.mybatis.platform.user.entity.UserInfo;
import com.mybatis.platform.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class InitEnvironment {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    private static String USER_MAP = "_USER_INFO";
    private static String ROLE_MAP = "_ROLE_INFO";
    private static String MENU_MAP = "_MENU_INFO";

    public void init() {
        initUser();
    }

    private void initUser() {
        Map<String, Object> arg0 = Maps.newConcurrentMap();
        arg0.put("status", SysConstant.DataStatus.VALID);
        List<User> users = userService.queryListByMap(arg0);
        Map<String, String> userMap = Maps.newConcurrentMap();
        Map<String, String> roleMap = Maps.newConcurrentMap();
        Map<String, String> menuMap = Maps.newConcurrentMap();
        for (User user : users) {
            UserInfo userInfo = BeanCopyUtils.copyObject(UserInfo.class, user);
            String loginName = user.getLoginName();
            List<Role> roleList = roleService.queryRoleByUserId(user.getId());
            if (roleList != null && !roleList.isEmpty())
                roleMap.put(loginName, JsonMapper.toJson(roleList));
            if (roleList != null && !roleList.isEmpty()) {
                List<Menu> menuList = menuService.queryMenuByRoleList(roleList);
                if (menuList != null && !menuList.isEmpty())
                    menuMap.put(loginName, JsonMapper.toJson(menuList));
            }
            userMap.put(loginName, JsonMapper.toJson(userInfo));
        }
        JedisUtil.setMap(USER_MAP, userMap, 0);
        JedisUtil.setMap(ROLE_MAP, roleMap, 0);
        JedisUtil.setMap(MENU_MAP, menuMap, 0);
        getUserInfo("yangzi");
    }

    public static UserInfo getUserInfo(String loginName) {
        Map<String, String> userMap = JedisUtil.getMap(USER_MAP);
        String json = userMap.get(loginName);
        UserInfo userInfo = JsonMapper.jsonToObject(json, UserInfo.class);
        List<Menu> menuList = getMenuInfo(loginName);
        userInfo.setMenuList(menuList);
        userInfo.setRoleInfos(getRoleInfo(loginName));
        return userInfo;
    }

    /**
     * 获取角色信息
     *
     * @param loginName
     * @return
     */
    public static List<Role> getRoleInfo(String loginName) {
        Map<String, String> roleMap = JedisUtil.getMap(ROLE_MAP);
        String json = roleMap.get(loginName);
        List<Role> roleList = JsonMapper.jsonToList(json, Role.class);
        return roleList;
    }

    /**
     * 获取菜单信息
     *
     * @param loginName
     * @return
     */
    public static List<Menu> getMenuInfo(String loginName) {
        Map<String, String> userMap = JedisUtil.getMap(MENU_MAP);
        String json = userMap.get(loginName);
        List<Menu> menuList = JsonMapper.jsonToList(json, Menu.class);
        return menuList;
    }
}
