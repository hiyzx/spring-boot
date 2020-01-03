package org.zero.redis.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zero.redis.util.RedisHelper;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author yezhaoxing
 * @date 2020/1/3
 */
@RestController
@RequestMapping("/lock")
@Slf4j
public class LockController {

    private static final String LOCK_KEY = "lock_key";

    private static final Integer EXPIRE_TIME = 100000;

    @Resource
    private RedisHelper<String, String> redisHelper;

    /**
     * 单次加锁
     *
     * @author yezhaoxing
     * @date 2020/1/3
     */
    @GetMapping("/one")
    public void one(@RequestParam String uid) {
        log.info("获取uuid");
        String uuid = UUID.randomUUID().toString();
        log.info("uuid={}", uuid);
        lock(uuid);
    }

    /**
     * 多次加锁
     *
     * @author yezhaoxing
     * @date 2020/1/3
     */
    @GetMapping("/two")
    public void two() {
        String uuid = UUID.randomUUID().toString();
        Long lockResult = redisHelper.lock(LOCK_KEY, uuid, EXPIRE_TIME);
        log.info("加锁结果={}", lockResult);
        try {
            // 重复加锁
            lock(uuid);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            Long unlockResult = redisHelper.unlock(LOCK_KEY, uuid, EXPIRE_TIME);
            log.info("解锁结果={}", unlockResult);
        }
    }

    private void lock(String uuid) {
        log.info("开始获取锁");
        Long lockResult = redisHelper.lock(LOCK_KEY, uuid, EXPIRE_TIME);
        if (lockResult > 0) {
            log.info("获取锁失败, 剩余时间:{}", lockResult);
            return;
        } else {
            log.info("加锁成功");
        }

        try {
            Thread.sleep(3000);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            Long unlockResult = redisHelper.unlock(LOCK_KEY, uuid, EXPIRE_TIME);
            log.info("解锁结果={}", unlockResult);
        }
    }
}
