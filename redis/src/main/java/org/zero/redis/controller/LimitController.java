package org.zero.redis.controller;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 水寒
 * @date 2020/12/26
 */
@RestController
@Slf4j
public class LimitController {

    @Resource
    private RedissonClient redissonClient;

    @GetMapping("/config")
    public void follow(@RequestParam String key, @RequestParam Long rate, @RequestParam Long interval) {
        RRateLimiter limit = redissonClient.getRateLimiter(key);
        RateLimiterConfig config = limit.getConfig();
        limit.setRate(RateType.OVERALL, rate, interval, RateIntervalUnit.SECONDS);
    }

    @GetMapping("/limit")
    public String follow(@RequestParam String key, @RequestParam Long acquire) {
        RRateLimiter limit = redissonClient.getRateLimiter(key);
        if (limit.tryAcquire(acquire)) {
            long l = limit.availablePermits();
            log.info("成功, 剩余令牌数: {}", l);
            return "成功";
        } else {
            log.info("失败");
            return "失败";
        }
    }
}
