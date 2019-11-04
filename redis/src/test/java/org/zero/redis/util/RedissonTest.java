package org.zero.redis.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RBucket;
import org.redisson.api.RLock;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.TimeUnit;
import java.util.stream.LongStream;

/**
 * @author yezhaoxing
 * @since 2018/09/28
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class RedissonTest {

    @Autowired
    private RedissonClient redissonClient;

    @Test
    public void test() throws InterruptedException {
        LongStream range = LongStream.range(0, 5);
        range.parallel().forEach( r -> {
            RLock lock = redissonClient.getLock("hello_hiyzx");
            log.info("lock start");
            try {
                boolean b = lock.tryLock(0, 5, TimeUnit.SECONDS);
                if(!b){
                    log.info("获取失败");
                    return;
                }
            } catch (Exception ex) {

            }
            log.info("lock end");
            lock.unlock();
            log.info("解锁");
        });
    }

    @Test
    public void test2() throws InterruptedException {
        RLock lock = redissonClient.getLock("hello_hiyzx");
        lock.lock();

        log.info("lock start");
        lock.lock(5, TimeUnit.SECONDS);
        log.info("lock end");
        lock.unlock();
    }

    @Test
    public void hset() {
        RBucket<Object> hi = redissonClient.getBucket("hi");
        hi.set("123");
        RMap<Object, Object> hello = redissonClient.getMap("hello");
        hello.fastPut("name", "yzx");
        hello.fastPut("age", 1);
    }
}