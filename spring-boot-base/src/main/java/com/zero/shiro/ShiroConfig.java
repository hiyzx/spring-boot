package com.zero.shiro;

import com.google.common.collect.Maps;
import com.zero.service.UserService;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.AnonymousFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.annotation.Resource;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import java.util.Map;

@Component
public class ShiroConfig {

    @Resource
    private RedisSessionDAO redisSessionDAO;
    @Resource
    private UserService userService;

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
        chains.put("/monitor/*", "anon");
        chains.put("/**", "authc");
        bean.setFilterChainDefinitionMap(chains);
        return bean;
    }

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        // 数据库认证的实现
        manager.setRealm(userRealm());
        // session 管理器
        manager.setSessionManager(sessionManager());
        // 缓存管理器
        manager.setCacheManager(shiroRedisCacheManager());
        return manager;
    }

    @Bean(name = "sessionManager")
    public SessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        // sessionManager.setSessionDAO(redisSessionDAO);
        sessionManager.setGlobalSessionTimeout(1800);
        return sessionManager;
    }

    @Bean
    @DependsOn(value = { "lifecycleBeanPostProcessor", "shiroRedisCacheManager" })
    public MyShiroRealm userRealm() {
        MyShiroRealm userRealm = new MyShiroRealm();
        userRealm.setCacheManager(shiroRedisCacheManager());
        userRealm.setCachingEnabled(true);
        userRealm.setAuthenticationCachingEnabled(false);// 禁用认证缓存
        userRealm.setAuthorizationCachingEnabled(true);
        return userRealm;
    }

    @Bean(name = "shiroRedisCacheManager")
    public ShiroRedisCacheManager shiroRedisCacheManager() {
        ShiroRedisCacheManager shiroRedisCacheManager = new ShiroRedisCacheManager();
        shiroRedisCacheManager.createCache("shiro_redis:");
        return shiroRedisCacheManager;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }
}