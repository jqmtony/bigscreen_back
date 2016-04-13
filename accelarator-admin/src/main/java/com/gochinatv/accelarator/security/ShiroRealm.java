package com.gochinatv.accelarator.security;

import java.util.ArrayList;
import java.util.List;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.gochinatv.accelarator.dao.entity.Permission;
import com.gochinatv.accelarator.dao.entity.User;
import com.gochinatv.accelarator.service.UserService;

@SuppressWarnings("all")
@Component
public class ShiroRealm extends AuthorizingRealm {
	
	@Autowired
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
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
		/*User user =  (User) principals.getPrimaryPrincipal();
		List<Permission> permissionList = null;
		
		try {
			permissionList = userService.getPermissionList(user.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<String> permissions = new ArrayList<String>();
		if(permissionList!=null){
			for(Permission ps:permissionList){
				permissions.add(ps.getCode());
			}
		}
		
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		simpleAuthorizationInfo.addStringPermissions(permissions);
		return simpleAuthorizationInfo;*/
		return null;
	}
	

	public void clearCached() {
		PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
		super.clearCache(principals);
	}

}
