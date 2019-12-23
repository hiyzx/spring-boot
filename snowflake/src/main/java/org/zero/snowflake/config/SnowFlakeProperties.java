package org.zero.snowflake.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConditionalOnProperty(name = "snowflake.enable", havingValue = "true")
@ConfigurationProperties(prefix = "snowflake")
public class SnowFlakeProperties {
    private Integer dataCenter;
    private Integer serviceId;
    private Integer instanceId;
}