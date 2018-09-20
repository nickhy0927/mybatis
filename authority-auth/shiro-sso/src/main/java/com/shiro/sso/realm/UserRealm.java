package com.shiro.sso.realm;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.google.common.collect.Lists;
import com.mybatis.core.orm.constant.SysConstant;
import com.mybatis.platform.role.entity.Role;
import com.mybatis.platform.role.service.RoleService;
import com.mybatis.platform.user.entity.User;
import com.mybatis.platform.user.service.UserService;

/**
 * 用户授权信息域
 * 
 * @author coderhuang
 * 
 */
public class UserRealm extends CasRealm {
	
	@Resource
	private RoleService roleService;

	@Resource
	private UserService userService;

	protected final Map<String, SimpleAuthorizationInfo> roles = new ConcurrentHashMap<String, SimpleAuthorizationInfo>();
	
	/**
	 * 设置角色和权限信息
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

		String account = (String) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo authorizationInfo = null;
		if (authorizationInfo == null) {
			authorizationInfo = new SimpleAuthorizationInfo();
			List<Role> roleList = roleService.queryRoleByUserId(account);
			List<String> roleCodes = Lists.newArrayList();
			List<String> roleNameList = Lists.newArrayList();
			for (Role role : roleList) {
				roleCodes.add(role.getCode());
				roleNameList.add(role.getName());
			}
			authorizationInfo.addStringPermissions(roleCodes);
			authorizationInfo.addRoles(roleNameList);
			roles.put(account, authorizationInfo);
		}

		return authorizationInfo;
	}
	
	
	/**
	 * 1、CAS认证 ,验证用户身份
	 * 2、将用户基本信息设置到会话中
	 */
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
		AuthenticationInfo authc = super.doGetAuthenticationInfo(token);
		String account = (String) authc.getPrincipals().getPrimaryPrincipal();
		User e = new User();
		e.setStatus(SysConstant.DataStatus.VALID);
		e.setLoginName(account);
		User user = userService.getObject(e);
		SecurityUtils.getSubject().getSession().setAttribute("user", user);
		return authc;
	}
	

}
