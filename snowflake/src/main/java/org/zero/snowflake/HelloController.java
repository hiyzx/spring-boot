package org.zero.snowflake;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zero.snowflake.config.Snowflake;
import org.zero.snowflake.config.SnowflakeProperties;


/**
 * @author yezhaoxing
 * @date 2019/12/25
 */
@RestController
@Slf4j
public class HelloController {

    @Autowired
    private Snowflake snowflake;
    @Autowired
    private SnowflakeProperties snowflakeProperties;

    @GetMapping("/hello")
    public String hello(){
        log.info(String.valueOf(snowflake.nextId()));
        return snowflakeProperties.toString();
    }
}
