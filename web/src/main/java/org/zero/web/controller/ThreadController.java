package org.zero.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yezhaoxing
 * @date 2019/9/19
 */
@RestController
public class ThreadController {

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    @GetMapping("/set")
    public String set(){
        contextHolder.set("hello");
        return "";
    }

    @GetMapping("/get")
    public String get(){
        return contextHolder.get();
    }

}
