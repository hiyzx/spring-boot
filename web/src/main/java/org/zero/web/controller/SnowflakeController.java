package org.zero.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zero.snowflake.config.Snowflake;
import org.zero.snowflake.config.SnowflakeProperties;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class SnowflakeController {

    @Autowired(required = false)
    private Snowflake snowflake;
    @Autowired(required = false)
    private SnowflakeProperties snowflakeProperties;

    @GetMapping("/snowflake")
    public String hello(){
        log.info(String.valueOf(snowflake.nextId()));
        return snowflakeProperties.toString();
    }
}