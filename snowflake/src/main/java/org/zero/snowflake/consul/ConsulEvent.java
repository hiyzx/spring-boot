package org.zero.snowflake.consul;

import lombok.Data;

@Data
public class ConsulEvent {
    private String serviceName;
    private String instanceId;
}
