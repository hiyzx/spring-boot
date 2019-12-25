package org.zero.snowflake.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@ConditionalOnProperty(name = "snowflake.enable")
@Configuration
public class SnowflakeConfiguration {

    @ConditionalOnProperty(name = "snowflake.enable", havingValue = "true")
    @Bean
    public Snowflake snowFlake() {
        return new Snowflake();
    }

}
