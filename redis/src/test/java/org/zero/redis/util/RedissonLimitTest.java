package org.zero.redis.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RRateLimiter;
import org.redisson.api.RateIntervalUnit;
import org.redisson.api.RateType;
import org.redisson.api.RedissonClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.util.concurrent.CountDownLatch;

/**
 * @author 水寒
 * @date 2020/12/26
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class RedissonLimitTest {

    @Resource
    private RedissonClient redissonClient;

    @Test
    public void test() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(11);
        RRateLimiter limit = redissonClient.getRateLimiter("limit");
        // 创建限流器 每秒钟产生10个令牌
        limit.trySetRate(RateType.OVERALL, 10, 1, RateIntervalUnit.SECONDS);
        for (int i = 0; i < 11; i++) {
            new Thread(() -> {
                if (limit.tryAcquire()) {
                    log.info("获取令牌成功");
                } else {
                    log.info("获取令牌失败");
                }
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
    }
}
