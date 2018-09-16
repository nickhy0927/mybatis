package com.authority.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.iss.platform.user.entity.UserInfo;

@SuppressWarnings("serial")
public class UserInfoEntity extends UserInfo implements UserDetails {
	
	private Set<Role> roles;

	private Map<String, List<Resource>> roleResources;

	/**
	 * Returns the authorites string
	 * eg. 
	 *    downpour --- ROLE_ADMIN,ROLE_USER
	 *    robbin --- ROLE_ADMIN
	 * @return
	 */
	public String getAuthoritiesString() {
	    List<String> authorities = new ArrayList<String>();
	    for(GrantedAuthority authority : this.getAuthorities())
	        authorities.add(authority.getAuthority());
	    return StringUtils.join(authorities, ",");
	}

	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// 根据自定义逻辑来返回用户权限，如果用户权限返回空或者和拦截路径对应权限不同，验证不通过
		if(!roles.isEmpty()){
			List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
			GrantedAuthority au = new SimpleGrantedAuthority("ROLE_USER");
			list.add(au);
			return list;
		}
		return null;
	}

	/* 
	 * 密码
	 */
	@Override
	public String getPassword() {
		return getPassword();
	}

	/*
	 * 帐号
	 * (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#getUsername()
	 */
	@Override
	public String getUsername() {
		return getLoginName();
	}

	/*
	 * 帐号是否不过期，false则验证不通过
	 * (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonExpired()
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/*
	 * 帐号是否不锁定，false则验证不通过
	 * (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonLocked()
	 */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	/*
	 * 凭证是否不过期，false则验证不通过
	 * (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#isCredentialsNonExpired()
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/*
	 * 该帐号是否启用，false则验证不通过
	 * (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#isEnabled()
	 */
	@Override
	public boolean isEnabled() {
		return getEnable() == 1 ? true : false;
	}


	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Map<String, List<Resource>> getRoleResources() {
		// init roleResources for the first time
		System.out.println("---------------------------------------------------");
		if(this.roleResources == null) {
			this.roleResources = new HashMap<String, List<Resource>>();
//			for(Role role : this.roles) {
//				String roleName = role.getName();
//				Set<Resource> resources = role.getResources();
//				for(Resource resource : resources) {
//					String key = roleName + "_" + resource.getType();
//					if(!this.roleResources.containsKey(key)) {
//						this.roleResources.put(key, new ArrayList<Resource>());
//					}
//					this.roleResources.get(key).add(resource);					
//				}
//			}
			
		}
		return this.roleResources;
	}

	public void setRoleResources(Map<String, List<Resource>> roleResources) {
		this.roleResources = roleResources;
	}

}
