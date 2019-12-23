package org.zero.snowflake.consul;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yezhaoxing
 * @date 2019/12/23
 */
@Component
public class ConsulCheck {

    @Value("${spring.cloud.consul.host}")
    private String consulHost;

    @Value("${spring.cloud.consul.port}")
    private String consulPort;

    @Value("${spring.cloud.client.ip-address}")
    private String localHost;

    @Value("${server.port}")
    private String localPort;

    private final ConcurrentHashMap<String, Integer> instanceMap = null;

    public String getInstance(){
        return null;
    }
}
