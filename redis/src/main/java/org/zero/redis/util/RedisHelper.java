package org.zero.redis.util;

import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Set;
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

    public void zSet(K key, V value, Integer score) {
        redisTemplate.opsForZSet().add(key, value, score);
    }

    public void zSet(K key, Set<ZSetOperations.TypedTuple<V>> var2) {
        redisTemplate.opsForZSet().add(key, var2);
    }

    public Double zGet(K key, V value) {
        return redisTemplate.opsForZSet().score(key, value);
    }

    public void zIncrease(K key, V value, Integer score) {
        redisTemplate.opsForZSet().incrementScore(key, value, score);
    }

    public Long zRank(K key, V value) {
        return redisTemplate.opsForZSet().reverseRank(key, value);
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

    public void clear() {
        redisTemplate.getConnectionFactory().getConnection().flushDb();
    }

    public Long size() {
        return redisTemplate.getConnectionFactory().getConnection().dbSize();
    }

    // 可以防止缓存击穿
    public Boolean setNx(final K key, final V value, Long expireTime) {
        return redisTemplate.execute((RedisConnection redisConnection) -> {
            RedisSerializer<K> keySerializer = (RedisSerializer<K>) redisTemplate.getKeySerializer();
            RedisSerializer<V> valueSerializer = (RedisSerializer<V>) redisTemplate.getValueSerializer();
            boolean flag = redisConnection.setNX(keySerializer.serialize(key), valueSerializer.serialize(value));
            if (expireTime > 0 && flag) {
                redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            }
            return flag;
        });
    }
}
