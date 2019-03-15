package com.atguigu.crud.shiro;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.atguigu.crud.bean.User;
import com.atguigu.crud.service.UserService;

public class ShiroRealm extends AuthorizingRealm{
	
	@Autowired
	UserService userService;
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		Object principal = principals.getPrimaryPrincipal();
		
		Set<String> roles = new HashSet<String>();
		if("admin".equals(principal)){
			roles.add("admin");
		}
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);
		
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
	
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		String username = upToken.getUsername();
		User user = userService.getUserByUserName(username);
		if(user==null){
			
			throw new UnknownAccountException("用户不存在!");
		
		}
		String principal = user.getUsername();
		String credentials = user.getPassword();
		String realmName = getName();
		SimpleAuthenticationInfo info = null;
		info = new SimpleAuthenticationInfo(principal, credentials, realmName);
		System.out.println("认证完成");
		return info;
	}

}
