package com.sl.one.config;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sl.one.shiro.ShiroDbRealm;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;

/**
 * Created by shi on 2017/1/11.
 */
@Configuration
public class ShiroConfig {

    private static Logger logger = LoggerFactory.getLogger(ShiroConfig.class);
    
  

    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(ShiroDbRealm.HASH_ALGORITHM);
        matcher.setHashIterations(ShiroDbRealm.HASH_INTERATIONS);
        return matcher;
    }

    @Bean
    public ShiroDbRealm shiroDbRealm() {
        logger.debug("注入ShiroDbRealm");
        ShiroDbRealm shiroDbRealm = new ShiroDbRealm();
        shiroDbRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return shiroDbRealm;
    }

    @Bean
    public EhCacheManager ehCacheManager() {
        logger.debug("注入shiroEhcacheManager");
        EhCacheManager ehCacheManager = new EhCacheManager();
        ehCacheManager.setCacheManagerConfigFile("classpath:ehcache/ehcache-shiro.xml");
        return ehCacheManager;
    }

    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        logger.debug("注入lifecycleBeanPostProcessor");
        return new LifecycleBeanPostProcessor();
    }

    @Bean(name = "rememberMeCookie")
    public SimpleCookie getSimpleCookie() {
        logger.debug("注入rememberMeCookie");
        SimpleCookie simpleCookie = new SimpleCookie("mypass");
        simpleCookie.setMaxAge(1000000);
        return simpleCookie;
    }

    @Bean(name = "rememberMeManager")
    public CookieRememberMeManager getCookieRememberMeManager() {
        logger.debug("注入rememberMeManager");
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(getSimpleCookie());
        return cookieRememberMeManager;
    }
    
  
    
    @Bean(name = "securityManager")
    public SecurityManager getDefaultWebSecurityManager() {
        logger.debug("注入securityManager");
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(shiroDbRealm());
        defaultWebSecurityManager.setRememberMeManager(getCookieRememberMeManager());
        defaultWebSecurityManager.setCacheManager(ehCacheManager());
        return defaultWebSecurityManager;
    }
    
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(SecurityManager securityManager) {
        logger.debug("注入shiroFilter,securityManager==" + getDefaultWebSecurityManager());
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setSuccessUrl("/bnpassinfo");
        Map<String, Filter> filters = new HashMap<String, Filter>();
        filters.put("authc", getFormAuthenticationFilter());
        shiroFilterFactoryBean.setFilters(filters);
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/logout", "logout");
        filterChainDefinitionMap.put("/login", "authc");
        filterChainDefinitionMap.put("/img/logo.png", "anon");
        filterChainDefinitionMap.put("/**", "user");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean(name = "formAuthenticationFilter")
    public FormAuthenticationFilter getFormAuthenticationFilter() {
        logger.debug("注入formAuthenticationFilter");
        FormAuthenticationFilter formAuthenticationFilter = new FormAuthenticationFilter();
        formAuthenticationFilter.setUsernameParam("username");
        formAuthenticationFilter.setPasswordParam("password");
        formAuthenticationFilter.setRememberMeParam("rememberMe");
        return formAuthenticationFilter;
    }

    
    
    // //会话Cookie模板
    // @Bean(name = "sessionIdCookie")
    // public SimpleCookie getSessionIdCookie() {
    // SimpleCookie simpleCookie = new SimpleCookie("sid");
    // simpleCookie.setHttpOnly(true);
    // simpleCookie.setMaxAge(-1);
    // return simpleCookie;
    // }


//    @Bean
//    public FilterRegistrationBean delegatingFilterProxy() {
//        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
//        DelegatingFilterProxy proxy = new DelegatingFilterProxy();
//        proxy.setTargetFilterLifecycle(true);
//        proxy.setTargetBeanName("shiroFilter");
//        filterRegistrationBean.setFilter(proxy);
//        return filterRegistrationBean;
//    }
    
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
       AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
       authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
       return authorizationAttributeSourceAdvisor;
    }
    
    /**  
     * ShiroDialect，为了在thymeleaf里使用shiro的标签的bean  
     * @return  
     */  
    @Bean  
    public ShiroDialect shiroDialect(){  
        return new ShiroDialect();  
    }
    
}
