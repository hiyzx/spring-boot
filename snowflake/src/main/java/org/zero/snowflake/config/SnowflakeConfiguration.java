package org.zero.snowflake.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@ConditionalOnProperty(name = "snowflake.enable", havingValue = "true")
@Configuration
public class SnowflakeConfiguration {

    @Bean
    public Snowflake snowFlake() {
        return new Snowflake();
    }

    @Bean
    public ZKClient zkClient(){
        return new ZKClient();
    }
}
