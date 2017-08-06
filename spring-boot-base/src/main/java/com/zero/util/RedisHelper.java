package com.zero.util;

import com.zero.vo.HealthCheckVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * redis工具类
 * 
 * @author yezhaoxing
 * @since 2017/7/17
 */
@Configuration
public class RedisHelper {

    private static final Logger LOG = LoggerFactory.getLogger(RedisHelper.class);
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    private static RedisHelper redisHelper;

    @PostConstruct
    public void init() {
        RedisSerializer<String> stringSerializer = new StringRedisSerializer();
        this.stringRedisTemplate.setKeySerializer(stringSerializer);
        this.stringRedisTemplate.setValueSerializer(stringSerializer);
        this.stringRedisTemplate.setHashKeySerializer(stringSerializer);
        this.stringRedisTemplate.setHashValueSerializer(stringSerializer);
        redisHelper = this;
        redisHelper.stringRedisTemplate = this.stringRedisTemplate;
    }

    private static StringRedisTemplate getStringRedisTemplate() {
        return redisHelper.stringRedisTemplate;
    }

    public static void set(String key, String value) throws Exception {
        getStringRedisTemplate().opsForValue().set(getRedisKey(key), value);
    }

    public static void set(String key, String value, long expireTime) throws Exception {
        getStringRedisTemplate().opsForValue().set(getRedisKey(key), value, expireTime);
    }

    public static void expire(String key, long expireTime) throws Exception {
        getStringRedisTemplate().expire(getRedisKey(key), expireTime, TimeUnit.MILLISECONDS);
    }

    public static String get(String key) throws Exception {
        return getStringRedisTemplate().opsForValue().get(getRedisKey(key));
    }

    public static void delete(String key) throws Exception {
        getStringRedisTemplate().delete(getRedisKey(key));
    }

    private static String getRedisKey(String key) {
        return String.format("student_%s", key);
    }

    public static HealthCheckVo checkRedisConnection() {
        HealthCheckVo healthCheckVo = new HealthCheckVo();
        healthCheckVo.setServiceName("redis");
        try {
            long startTimeMillis = System.currentTimeMillis();
            RedisHelper.set(String.format("%scheckRedisConnection", getRedisKey("")),
                    DateHelper.format(new Date(startTimeMillis), "yyyy-MM-dd HH:mm:ss"));
            healthCheckVo.setNormal(true);
            healthCheckVo.setCostTime(String.format("%sms", System.currentTimeMillis() - startTimeMillis));
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            healthCheckVo.setNormal(false);
        }
        return healthCheckVo;
    }
}
