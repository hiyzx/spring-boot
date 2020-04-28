package org.zero.grpc.client;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author 水寒
 * @date 2020/4/27
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class HelloControllerTest {

    @Autowired
    private UserController helloController;

    @Test
    void hello() {

    }
}