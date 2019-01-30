package org.zero.spring.mybatis.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zero.spring.mybatis.po.User;
import org.zero.spring.mybatis.service.UserService;

/**
 * @author yezhaoxing
 * @since 2018/09/28
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Test
    public void save() {
        User user = new User();
        user.setId(4);
        user.setAge(5);
        userService.update(user);
    }

    @Test
    public void get() {
        User user = userService.findById(4);

    }

}