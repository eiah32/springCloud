package com.eiah.config;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

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
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.eiah.domain.Role;
import com.eiah.domain.User;
import com.eiah.service.UserService;

public class UserRealm extends AuthorizingRealm {
	@Autowired
	private UserService userService;
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String username = (String)principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();  
        List<User> list = userService.findUserAllInfoByUsername(username);
        // 获取request
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        // 用户信息
        request.getSession().setAttribute("user", list.get(0));
        // 角色权限
        Set<String> roleSet = new HashSet<>();
        Set<String> permissionSet = new HashSet<>();
        for (int i = 0; i < list.size(); i++) {
        	Role listRole = ( (User)list.get(i) ).getRole();
        	roleSet.add(listRole.getRoleType());
        	permissionSet.add(listRole.getPermission().getUrl());
		}
        authorizationInfo.setRoles(roleSet);
        authorizationInfo.setStringPermissions(permissionSet);
        return authorizationInfo;
		
	}

	/**
	 * 验证当前登录的Subject LoginController.login()方法中执行Subject.login()时 执行此方法
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
			throws AuthenticationException {
		User user = userService.findByUsername((String) authcToken.getPrincipal());  
	    if(user == null)
	        throw new UnknownAccountException();//没找到帐号  
	    if("0".equals(user.getStatus()))
	        throw new LockedAccountException(); //帐号锁定  
	    
		// 交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
		return new SimpleAuthenticationInfo(
				user.getUsername(), 
				user.getPassword(),
				ByteSource.Util.bytes(user.getCredentialsSalt()),
				getName()
		);
		
	}
	
}
