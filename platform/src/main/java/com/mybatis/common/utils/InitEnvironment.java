package com.mybatis.common.utils;

import com.google.common.collect.Maps;
import com.mybatis.core.orm.constant.SysConstant;
import com.mybatis.platform.menu.entity.Menu;
import com.mybatis.platform.menu.service.MenuService;
import com.mybatis.platform.role.entity.Role;
import com.mybatis.platform.role.service.RoleService;
import com.mybatis.platform.user.entity.User;
import com.mybatis.platform.user.entity.UserInfo;
import com.mybatis.platform.user.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class InitEnvironment {

    private String unauthUrl;// 未认证的页面
    private String initPassword;

    private String initUsername;

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
                roleMap.put(loginName, new JsonMapper().toJson(roleList));
            if (roleList != null && !roleList.isEmpty()) {
                List<Menu> menuList = menuService.queryMenuByRoleList(roleList);
                if (menuList != null && !menuList.isEmpty())
                    menuMap.put(loginName, new JsonMapper().toJson(menuList));
            }
            userMap.put(loginName, new JsonMapper().toJson(userInfo));
        }
        JedisUtil.setMap(USER_MAP, userMap, 0);
        JedisUtil.setMap(ROLE_MAP, roleMap, 0);
        JedisUtil.setMap(MENU_MAP, menuMap, 0);
        getUserInfo("yangzi");
    }

    public static UserInfo getUserInfo(String loginName) {
        Map<String, String> userMap = JedisUtil.getMap(USER_MAP);
        UserInfo userInfo;
        if (userMap != null) {
            String json = userMap.get(loginName);
            if (StringUtils.isNoneEmpty(json)) {
                userInfo = new JsonMapper().fromJson(json, UserInfo.class);
                List<Menu> menuList = getMenuInfo(loginName);
                userInfo.setMenuList(menuList);
                userInfo.setRoleInfos(getRoleInfo(loginName));
            } else userInfo = new UserInfo();
        } else userInfo = new UserInfo();
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
        new JsonMapper();
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

    public String getUnauthUrl() {
        return unauthUrl;
    }

    public void setUnauthUrl(String unauthUrl) {
        this.unauthUrl = unauthUrl;
    }

    public String getInitPassword() {
        return initPassword;
    }

    public void setInitPassword(String initPassword) {
        this.initPassword = initPassword;
    }

    public String getInitUsername() {
        return initUsername;
    }

    public void setInitUsername(String initUsername) {
        this.initUsername = initUsername;
    }
}
