package com.zero.shiro;

import org.apache.shiro.cache.AbstractCacheManager;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

public class ShiroRedisCacheManager extends AbstractCacheManager {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    protected Cache<String, Object> createCache(String name) throws CacheException {
        return new ShiroCache<>(redisTemplate, name);
    }
}
