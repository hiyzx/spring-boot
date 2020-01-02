package org.zero.redis.util;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Set;

/**
 * redis工具类-操作set数据结构
 *
 * @author yezhaoxing
 * @date 2017/7/17
 */
@Component
public class RedisSetHelper<K, V> {

    @Resource
    private RedisTemplate<K, V> redisTemplate;


    /**
     *  往set集合添加数据
     * @param key 存储的key
     * @param value 存储的值
     */
    public void add(K key, V... value) {
        redisTemplate.opsForSet().add(key, value);
    }

    /**
     *  set集合删除数据
     * @param key 存储的key
     * @param value 存储的值
     */
    public void remove(K key, V... value) {
        redisTemplate.opsForSet().remove(key, value);
    }


    /**
     *  查询集合
     * @param key 存储的key
     */
    public Set<V> members(K key) {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     *  判断是否在集合中
     * @param key 存储的key
     */
    public Boolean isMember(K key, V value) {
        return redisTemplate.opsForSet().isMember(key, value);
    }

    /**
     *  求key1 key2集合交集
     */
    public Set<V> intersect(K key1, K key2) {
        return redisTemplate.opsForSet().intersect(key1, key2);
    }

    /**
     *  返回集合中的数量
     */
    public Long size(K key) {
        return redisTemplate.opsForSet().size(key);
    }

}
