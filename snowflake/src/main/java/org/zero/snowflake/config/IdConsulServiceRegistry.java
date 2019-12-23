package org.zero.snowflake.config;

import com.ecwid.consul.v1.ConsulClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.consul.discovery.ConsulDiscoveryProperties;
import org.springframework.cloud.consul.discovery.HeartbeatProperties;
import org.springframework.cloud.consul.discovery.TtlScheduler;
import org.springframework.cloud.consul.serviceregistry.ConsulRegistration;
import org.springframework.cloud.consul.serviceregistry.ConsulServiceRegistry;
import org.zero.snowflake.consul.ConsulEvent;
import org.zero.snowflake.consul.ConsulCheck;

public class IdConsulServiceRegistry extends ConsulServiceRegistry {
    public IdConsulServiceRegistry(ConsulClient client, ConsulDiscoveryProperties properties, TtlScheduler ttlScheduler,
        HeartbeatProperties heartbeatProperties) {
        super(client, properties, ttlScheduler, heartbeatProperties);
    }

    @Autowired
    private ConsulCheck consulCheck;

    @Override
    public void register(ConsulRegistration reg) {
        ConsulEvent consulEvent = new ConsulEvent();
        consulEvent.setServiceName(reg.getService().getName());
        super.register(reg);
        consulEvent.setInstanceId(reg.getInstanceId());
        consulEvent.setInstanceId(consulCheck.getInstance());
    }
}
