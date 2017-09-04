package com.zero.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description 输错5次密码锁定半小时
 * @author yezhaoxing
 * @date 2017/08/25
 */
public class RetryLimitCredentialsMatcher extends HashedCredentialsMatcher implements InitializingBean {
    private final static String DEFAULT_CHACHE_NAME = "retryLimitCache";

    private final CacheManager cacheManager;
    private String retryLimitCacheName;
    private Cache<String, AtomicInteger> passwordRetryCache;
    // 加密方法
    @Value("${passwordHash.algorithmName}")
    private String algorithmName;
    // 加密次数
    @Value("${passwordHash.hashIterations}")
    private int hashIterations;

    public RetryLimitCredentialsMatcher(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
        this.retryLimitCacheName = DEFAULT_CHACHE_NAME;
    }

    public String getRetryLimitCacheName() {
        return retryLimitCacheName;
    }

    public void setRetryLimitCacheName(String retryLimitCacheName) {
        this.retryLimitCacheName = retryLimitCacheName;
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken authcToken, AuthenticationInfo info) {
        String username = (String) authcToken.getPrincipal();
        // retry count + 1
        AtomicInteger retryCount = passwordRetryCache.get(username);
        if (retryCount == null) {
            retryCount = new AtomicInteger(0);
            passwordRetryCache.put(username, retryCount);
        }
        if (retryCount.incrementAndGet() > 5) {
            throw new ExcessiveAttemptsException(String.format("用户名: %s 密码连续输入错误超过5次，锁定半小时！", username));
        } else {
            passwordRetryCache.put(username, retryCount);
        }

        boolean matches = super.doCredentialsMatch(authcToken, info);
        if (matches) {
            // clear retry data
            passwordRetryCache.remove(username);
        }
        return matches;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        super.setHashAlgorithmName(algorithmName);
        super.setHashIterations(hashIterations);
        this.passwordRetryCache = cacheManager.getCache(retryLimitCacheName);
    }
}
