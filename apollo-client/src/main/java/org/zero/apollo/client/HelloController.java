package org.zero.apollo.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yezhaoxing
 * @date 2019/10/8
 */
@RestController
public class HelloController {

    @Value("${name}")
    private String name;

    @GetMapping("/hello")
    public String hello(){
        return name;
    }
}
