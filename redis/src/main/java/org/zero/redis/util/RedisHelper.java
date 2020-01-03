package org.zero.redis.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * redis工具类
 *
 * @author yezhaoxing
 * @date 2017/7/17
 */
@Component
public class RedisHelper<K, V> {

    @Resource
    private RedisTemplate<K, V> redisTemplate;

    public void set(K key, V value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public void set(K key, V value, long expireTime) {
        this.set(key, value);
        this.expire(key, expireTime);
    }

    public void expire(K key, long expireTime) {
        redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
    }

    public V get(K key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void delete(K key) {
        redisTemplate.delete(key);
    }

    // 加锁
    public Long lock(final K key, final String value, Integer expireTime) {
        DefaultRedisScript<Long> script = new DefaultRedisScript<>();
        script.setResultType(Long.class);
        script.setScriptSource(new ResourceScriptSource(new ClassPathResource("lock.lua")));
        List<K> keys = Collections.singletonList(key);
        return redisTemplate.execute(script, keys, expireTime, value);
    }

    // 解锁
    public Long unlock(final K key, final String value, Integer expireTime) {
        DefaultRedisScript<Long> script = new DefaultRedisScript<>();
        script.setResultType(Long.class);
        script.setScriptSource(new ResourceScriptSource(new ClassPathResource("unlock.lua")));
        List<K> keys = Collections.singletonList(key);
        return redisTemplate.execute(script, keys, expireTime, value);
    }
}
