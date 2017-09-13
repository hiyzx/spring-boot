package com.zero.activemq;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.jms.Queue;

/**
 * @author yezhaoxing
 * @date 2017/09/13
 */
@Component
public class MQConfig {

    public static final String QUEUE = "spring-boot.queue";

    @Bean
    public Queue queue() {
        return new ActiveMQQueue(QUEUE);
    }
}
