package org.zero.mybatis.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zero.mybatis.po.User;

import javax.annotation.Resource;

/**
 * @author yezhaoxing
 * @since 2018/11/07
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest {

    @Resource
    private UserService userService;

    @Test
    public void update() {
        User user = new User();
        user.setId(2);
        user.setName("hiyzx");
        userService.update(user);
    }

    @Test
    public void select() {
        User user = userService.select(1);
    }
}