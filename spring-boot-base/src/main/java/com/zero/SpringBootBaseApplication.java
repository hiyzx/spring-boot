package com.zero;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@MapperScan(basePackages = "com.zero.dao")
public class SpringBootBaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootBaseApplication.class, args);
    }
}
