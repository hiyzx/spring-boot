package com.zero.shiro;

import com.google.common.collect.Maps;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.AnonymousFilter;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
        filterRegistration.setFilter(new DelegatingFilterProxy("shiroFilter"));
        filterRegistration.setEnabled(true);
        filterRegistration.addUrlPatterns("/*");
        filterRegistration.setDispatcherTypes(DispatcherType.REQUEST);
        return filterRegistration;
    }

    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter() {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager());
        bean.setLoginUrl("/auth/toLogin.json");
        bean.setUnauthorizedUrl("/auth/unAuthor.json");

        Map<String, Filter> filters = Maps.newHashMap();
        filters.put("anon", new AnonymousFilter());
        bean.setFilters(filters);

        Map<String, String> chains = Maps.newHashMap();
        chains.put("/swagger-ui.html", "anon");
        chains.put("/swagger-resources/**", "anon");
        chains.put("/v2/api-docs", "anon");
        chains.put("/webjars/**", "anon");
        chains.put("/auth/*", "anon");
        chains.put("/test/*", "anon");
        chains.put("/monitor/*", "anon");
        chains.put("/user/getUserInfo.json", "user");
        chains.put("/**", "authc");
        bean.setFilterChainDefinitionMap(chains);
        return bean;
    }

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        // 数据库认证的实现
        manager.setRealm(myShiroRealm());
        // session 管理器
        manager.setSessionManager(sessionManager());
        // 缓存管理器
        manager.setCacheManager(shiroRedisCacheManager());
        // 记住我
        manager.setRememberMeManager(cookieRememberMeManager());
        return manager;
    }

    @Bean(name = "sessionManager")
    @DependsOn("redisSessionDAO")
    public SessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(sessionDAO());
        sessionManager.setGlobalSessionTimeout(1000 * 60 * 30);
        return sessionManager;
    }

    @Bean(name = "redisSessionDAO")
    @DependsOn("shiroRedisCacheManager")
    public RedisSessionDAO sessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setCacheManager(shiroRedisCacheManager());
        return redisSessionDAO;
    }

    @Bean(name = "rememberMeManager")
    @DependsOn(value = { "rememberMeCookie" })
    public CookieRememberMeManager cookieRememberMeManager() {
        CookieRememberMeManager rememberMeManager = new CookieRememberMeManager();
        rememberMeManager.setCipherKey(Base64.decode("4AvVhmFLUs0KTA3Kprsdag=="));
        rememberMeManager.setCookie(rememberMeCookie());
        return rememberMeManager;
    }

    @Bean(name = "rememberMeCookie")
    public SimpleCookie rememberMeCookie() {
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        simpleCookie.setHttpOnly(true);
        simpleCookie.setMaxAge(2592000);
        return simpleCookie;
    }

    @Bean
    @DependsOn(value = { "lifecycleBeanPostProcessor", "shiroRedisCacheManager", "credentialsMatcher" })
    public MyShiroRealm myShiroRealm() {
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        myShiroRealm.setCredentialsMatcher(retryLimitCredentialsMatcher());
        myShiroRealm.setCacheManager(shiroRedisCacheManager());
        myShiroRealm.setCachingEnabled(true);
        myShiroRealm.setAuthenticationCachingEnabled(false);// 禁用认证缓存
        myShiroRealm.setAuthorizationCachingEnabled(true);
        return myShiroRealm;
    }

    @Bean(name = "shiroRedisCacheManager")
    public ShiroRedisCacheManager shiroRedisCacheManager() {
        ShiroRedisCacheManager shiroRedisCacheManager = new ShiroRedisCacheManager();
        shiroRedisCacheManager.createCache("redis:");
        return shiroRedisCacheManager;
    }

    @Bean(name = "passwordHash")
    public PasswordHash passwordHash() {
        return new PasswordHash();
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean(name = "credentialsMatcher")
    public RetryLimitCredentialsMatcher retryLimitCredentialsMatcher() {
        return new RetryLimitCredentialsMatcher(shiroRedisCacheManager());
    }
}