package com.zero.handler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class HandlerApplication {

    public static void main(String[] args) {
        SpringApplication.run(HandlerApplication.class, args);
    }
}
