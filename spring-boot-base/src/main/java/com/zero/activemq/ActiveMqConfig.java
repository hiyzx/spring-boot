package com.zero.activemq;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.jms.Queue;
import javax.jms.Topic;

/**
 * @author yezhaoxing
 * @date 2017/09/09
 */
@Component
public class ActiveMqConfig {

    @Bean
    public Queue queue() {
        return new ActiveMQQueue("spring-boot.queue");
    }

    @Bean
    public Topic topic() {
        return new ActiveMQTopic("spring-boot.topic");
    }
}
