package org.zero.shiro.core;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/logout", "logout");
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setLoginUrl("/toLogin");
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public MyShiroRealm myShiroRealm() {
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return myShiroRealm;
    }

    @Bean
    public SimpleCookie simpleCookie() {
        SimpleCookie simpleCookie = new SimpleCookie("SHAREJSESSIONID");
        simpleCookie.setPath("/");
        simpleCookie.setHttpOnly(true);
        return simpleCookie;
    }

    @Bean
    public DefaultWebSessionManager webSessionManager() {
        DefaultWebSessionManager webSessionManager = new DefaultWebSessionManager();
        webSessionManager.setGlobalSessionTimeout(120000);
        webSessionManager.setDeleteInvalidSessions(true);
        webSessionManager.setSessionValidationInterval(1800000);
        webSessionManager.setSessionIdCookie(simpleCookie());
        webSessionManager.setSessionValidationSchedulerEnabled(false);
        return webSessionManager;
    }

    @Bean
    public DefaultWebSecurityManager webSecurityManager() {
        DefaultWebSecurityManager webSecurityManager = new DefaultWebSecurityManager();
        webSecurityManager.setRealm(myShiroRealm());
        webSecurityManager.setSessionManager(webSessionManager());
        return webSecurityManager;
    }

    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(2);//散列的次数，比如散列两次，相当于 md5(md5(""));
        return hashedCredentialsMatcher;
    }

}