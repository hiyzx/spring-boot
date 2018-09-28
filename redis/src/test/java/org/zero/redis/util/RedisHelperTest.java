package org.zero.redis.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author yezhaoxing
 * @since 2018/09/28
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class RedisHelperTest {

    @Autowired
    private RedisHelper<String, Object> redisHelper;

    @Test
    public void set() {
        redisHelper.set("name", "zero");
        redisHelper.set("user", new UserDto("zero", "2"));
    }

    @Test
    public void get() {
        log.info((String) redisHelper.get("name"));
        UserDto user = (UserDto) redisHelper.get("user");
        log.info(user.toString());
    }
}