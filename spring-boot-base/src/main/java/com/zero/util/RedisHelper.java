package com.zero.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * redis工具类
 * 
 * @author yezhaoxing
 * @date 2017/7/17
 */
@Configuration
public class RedisHelper<K, V> {

    @Resource
    private RedisTemplate<K, V> redisTemplate;

    public void set(K key, V value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public void set(K key, V value, long expireTime) {
        redisTemplate.opsForValue().set(key, value, expireTime);
    }

    public void expire(K key, long expireTime) {
        redisTemplate.expire(key, expireTime, TimeUnit.MILLISECONDS);
    }

    public V get(K key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void delete(K key) {
        redisTemplate.delete(key);
    }
}
