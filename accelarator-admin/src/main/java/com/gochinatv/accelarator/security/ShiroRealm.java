package com.gochinatv.accelarator.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.gochinatv.accelarator.dao.entity.Permission;
import com.gochinatv.accelarator.dao.entity.Resource;
import com.gochinatv.accelarator.dao.entity.User;
import com.gochinatv.accelarator.service.ResourceService;
import com.gochinatv.accelarator.service.UserService;

@SuppressWarnings("all")
@Component
public class ShiroRealm extends AuthorizingRealm {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ResourceService resourceService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public void setResourceService(ResourceService resourceService) {
		this.resourceService = resourceService;
	}

	@Override
	public void setName(String name) {
		super.setName("shiroRealm");
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String userName = (String) token.getPrincipal();
		User user = null;
		SimpleAuthenticationInfo simpleAuthenticationInfo = null;
		try {
			user = userService.getLoginUser(userName);
			if(user == null) {
	            throw new UnknownAccountException();//没找到帐号
	        }else if(user.getStatus()==2){
	        	throw new LockedAccountException();
	        }
			
			String password = user.getPassword();
			simpleAuthenticationInfo = new SimpleAuthenticationInfo(user, password, this.getName());
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return simpleAuthenticationInfo;
	}
	

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// 因为非正常退出，即没有显式调用 SecurityUtils.getSubject().logout()
		// (可能是关闭浏览器，或超时)，但此时缓存依旧存在(principals)，所以会自己跑到授权方法里。
		/*if (!SecurityUtils.getSubject().isAuthenticated()) {
			doClearCache(principals);
			SecurityUtils.getSubject().logout();
			return null;
		}*/
		User user =  (User) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		
		List<Resource> resourceList = null;
		try {
			resourceList = resourceService.getUserResourceList(user.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<String> permissions = new ArrayList<String>();
		for (Resource resource : resourceList) {
			permissions.add(resource.getUrl());
		}
		info.addStringPermissions(permissions);
		return info;
	}
	

	public void clearCached() {
		PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
		super.clearCache(principals);
	}
   
}
