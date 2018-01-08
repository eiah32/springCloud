package com.eiah.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfig {
	/**
	 * Shiro的Web过滤器Factory 命名:shiroFilter<br />
	 * 
	 * @param securityManager
	 * @return
	 */
	@Bean(name = "shiroFilter")
	public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

		// Shiro的核心安全接口,这个属性是必须的
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		// 要求登录时的链接(可根据项目的URL进行替换),非必须的属性,默认会自动寻找Web工程根目录下的"/login.jsp"页面
		shiroFilterFactoryBean.setLoginUrl("/user/login");
		// 登录成功后要跳转的连接,逻辑也可以自定义，例如返回上次请求的页面
		shiroFilterFactoryBean.setSuccessUrl("/");
		// 用户访问未对其授权的资源时,所显示的连接
		shiroFilterFactoryBean.setUnauthorizedUrl("/pages/403");
		/*
		 * 定义shiro过滤器,例如实现自定义的FormAuthenticationFilter，
		 * 需要继承FormAuthenticationFilter 本例中暂不自定义实现，在下一节实现验证码的例子中体现
		 */

		/*
		 * 定义shiro过滤链 Map结构
		 * Map中key(xml中是指value值)的第一个'/'代表的路径是相对于HttpServletRequest.
		 * getContextPath()的值来的
		 * anon：它对应的过滤器里面是空的,什么都没做,这里.do和.jsp后面的*表示参数,比方说login.jsp?main这种
		 * authc：该过滤器下的页面必须验证后才能访问,它是Shiro内置的一个拦截器org.apache.shiro.web.filter.
		 * authc.FormAuthenticationFilter
		 */
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
		// 配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
		filterChainDefinitionMap.put("/user/logout", "logout");

		// 配置记住我或认证通过可以访问的地址
		filterChainDefinitionMap.put("/index", "user");
		filterChainDefinitionMap.put("/", "user");
		
		// <!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
		// <!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
//		filterChainDefinitionMap.put("/user/**", "anon");
//		filterChainDefinitionMap.put("/css/**", "anon");
		filterChainDefinitionMap.put("/js/**", "anon");
//		filterChainDefinitionMap.put("/images/**", "anon");
//		filterChainDefinitionMap.put("/jquery-easyui-1.5.1/**", "anon");
		
//		filterChainDefinitionMap.put("/**", "authc");
		filterChainDefinitionMap.put("/**", "anon");

		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

		return shiroFilterFactoryBean;
	}

	/**
	 * Shiro Realm 继承自AuthorizingRealm的自定义Realm,即指定Shiro验证用户登录的类为自定义的
	 * 
	 * @param cacheManager
	 * @return
	 */
	@Bean
	public UserRealm userRealm() {
		UserRealm userRealm = new UserRealm();
		// 告诉realm,使用credentialsMatcher加密算法类来验证密文
		userRealm.setCredentialsMatcher(hashedCredentialsMatcher());
		return userRealm;
	}

	/**
	 * 凭证匹配器 （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
	 * 所以我们需要修改下doGetAuthenticationInfo中的代码; ） 可以扩展凭证匹配器，实现 输入密码错误次数后锁定等功能，下一次
	 * 
	 * @return
	 */
	@Bean(name = "credentialsMatcher")
	public HashedCredentialsMatcher hashedCredentialsMatcher() {
		HashedCredentialsMatcher hashedCredentialsMatcher = 
				new RetryLimitHashedCredentialsMatcher(getEhCacheManager());
		hashedCredentialsMatcher.setHashAlgorithmName("md5");// 散列算法:这里使用MD5算法;
		hashedCredentialsMatcher.setHashIterations(2);// 散列的次数，比如散列两次，相当于// md5(md5(""));
		// storedCredentialsHexEncoded默认是true，此时用的是密码加密用的是Hex编码；false时用Base64编码
		hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
		return hashedCredentialsMatcher;
	}

	/**
	 * Shiro生命周期处理器
	 * 
	 * @return
	 */
//	@Bean
//	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
//		return new LifecycleBeanPostProcessor();
//	}

	/**
	 * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
	 * 配置以下两个bean(DefaultAdvisorAutoProxyCreator(可选)和AuthorizationAttributeSourceAdvisor)即可实现此功能
	 * 
	 * @return
	 */
//	@Bean
//	@DependsOn({ "lifecycleBeanPostProcessor" })
//	public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
//		DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
//		advisorAutoProxyCreator.setProxyTargetClass(true);
//		return advisorAutoProxyCreator;
//	}

	@Bean(name = "securityManager")  
    public DefaultWebSecurityManager defaultWebSecurityManager(UserRealm userRealm){  
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();  
        //设置realm  
        securityManager.setRealm(userRealm); 
     // 自定义session管理 使用redis
//    	securityManager.setSessionManager(SessionManager());
        // 注入记住我管理器
        securityManager.setRememberMeManager(rememberMeManager());
        // 注入缓存管理器
        securityManager.setCacheManager(getEhCacheManager());  
        return securityManager;  
    } 
	
//	@Bean
//	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
//		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
//		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
//		return authorizationAttributeSourceAdvisor;
//	}

	@Bean
	public EhCacheManager getEhCacheManager() {
		EhCacheManager ehcacheManager = new EhCacheManager();
		ehcacheManager.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");
		return ehcacheManager;
	}
	
	/**
	 * cookie对象;
	 * @return
	 */
	public SimpleCookie rememberMeCookie(){
	   //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
	   SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
	   //<!-- 记住我cookie生效时间30天 ,单位秒;-->
	   simpleCookie.setMaxAge(2592000);
	   return simpleCookie;
	}
	/**
	 * cookie管理对象;记住我功能
	 * @return
	 */
	public CookieRememberMeManager rememberMeManager(){
	   CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
	   cookieRememberMeManager.setCookie(rememberMeCookie());
	   //rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
	   cookieRememberMeManager.setCipherKey(Base64.decode("a69ec781563b1a5d791f7b2bdd117a36"));
	   return cookieRememberMeManager;
	}

}
