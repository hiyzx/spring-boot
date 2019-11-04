package com.zero.handler.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = 1)
public class MyApplicationRunner implements ApplicationRunner {

    @Autowired
    private NodeHelper nodeHelper;

    @Override
    public void run(ApplicationArguments var1) {
        nodeHelper.initNodes();
    }

}