package com.zero.shiro;

import org.apache.shiro.cache.AbstractCacheManager;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

public class ShiroRedisCacheManager extends AbstractCacheManager {

    @Resource
    private RedisTemplate<byte[], byte[]> redisTemplate;

    @Override
    protected Cache<byte[], byte[]> createCache(String name) throws CacheException {
        return new ShiroRedisCache<>(redisTemplate, name);
    }
}
