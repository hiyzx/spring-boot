package org.zero.snowflake.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.consul.discovery.ConsulDiscoveryProperties;
import org.springframework.cloud.consul.discovery.HeartbeatProperties;
import org.springframework.cloud.consul.discovery.TtlScheduler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ecwid.consul.v1.ConsulClient;

@ConditionalOnProperty(name = "id.snowflake.enable")
@Configuration
public class IdConfiguration {
    @Value("spring.application.name")
    private String serviceName;

    @Autowired(required = false)
    private TtlScheduler ttlScheduler;

    @ConditionalOnProperty(name = "id.snowflake.enable", havingValue = "true")
    @Bean
    public SnowFlake snowFlake() {
        return new SnowFlake();
    }

    @Bean
    @ConditionalOnMissingBean
    public IdConsulServiceRegistry consulServiceRegistry(ConsulClient consulClient,
        ConsulDiscoveryProperties properties, HeartbeatProperties heartbeatProperties) {
        return new IdConsulServiceRegistry(consulClient, properties, this.ttlScheduler, heartbeatProperties);
    }
}
