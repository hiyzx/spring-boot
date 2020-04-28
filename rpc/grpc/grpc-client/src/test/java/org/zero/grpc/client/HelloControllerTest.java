package org.zero.grpc.client;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

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
        RestTemplate restTemplate = new RestTemplate();
        String resp = restTemplate.getForObject("http://localhost:8081/hello?name=2", String.class);
        System.out.println(resp);
        Assert.assertNotNull(helloController.hello("123"));

    }
}