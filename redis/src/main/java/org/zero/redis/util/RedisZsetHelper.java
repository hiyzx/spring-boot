package org.zero.redis.util;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * redis工具类-操作zset数据结构
 *
 * @author yezhaoxing
 * @date 2017/7/17
 */
@Component
public class RedisZsetHelper<K, V> {

    @Resource
    private RedisTemplate<K, V> redisTemplate;

    /**
     * 在zset集合添加数据,score会影响排名.value相同,score会覆盖
     * 
     * @param key
     *            存储的key
     * @param value
     *            存储的值
     * @param score
     *            设置分数
     */
    public void add(K key, V value, Integer score) {
        redisTemplate.opsForZSet().add(key, value, score);
    }

    /**
     *  删除value
     * @param key 存储的key
     * @param value 存储的值
     */
    public void remove(K key, V value) {
        redisTemplate.opsForZSet().remove(key, value);
    }

    /**
     * 查询分数值
     * 
     * @param key
     *            存储的key
     * @param value
     *            存储的值
     */
    public Double getScore(K key, V value) {
        return redisTemplate.opsForZSet().score(key, value);
    }

    /**
     * 根据value来添加score,修改排名
     * 
     * @param key
     *            存储的key
     * @param value
     *            存储的值
     * @param score
     *            要修改的分数
     */
    public void incrScore(K key, V value, Integer score) {
        redisTemplate.opsForZSet().incrementScore(key, value, score);
    }

    /**
     * 获取排名
     * 
     * @param key
     *            存储的key
     * @param value
     *            存储的值
     * @return 排名(从0开始)
     */
    public Long getRank(K key, V value) {
        return redisTemplate.opsForZSet().reverseRank(key, value);
    }

}
