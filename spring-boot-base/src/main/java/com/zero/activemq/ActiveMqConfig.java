package com.zero.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.connection.JmsTransactionManager;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;

/**
 * @author yezhaoxing
 * @date 2017/09/09
 */
@Component
@EnableJms
public class ActiveMqConfig {

    @Bean
    public ConnectionFactory connectionFactory() {

        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL("tcp://127.0.0.1:61616");
        connectionFactory.setUserName("admin");
        connectionFactory.setPassword("admin");
        connectionFactory.setAlwaysSyncSend(false);
        connectionFactory.setUseAsyncSend(true);
        return connectionFactory;
    }

    @Bean
    public JmsTemplate jmsQueueTemplate() {

        return new JmsTemplate(connectionFactory());

    }

    @Bean
    public Queue queue() {
        return new ActiveMQQueue("spring-boot.queue");
    }

    @Bean(name = "ptpContainer")
    public DefaultJmsListenerContainerFactory jmsQueueListenerContainerFactory() {

        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        // 设置连接数
        factory.setConcurrency("3-10");
        // 重连间隔时间
        factory.setRecoveryInterval(1000L);
        return factory;
    }

    @Bean
    public JmsTransactionManager jmsTransactionManager() {
        return new JmsTransactionManager(connectionFactory());
    }

}
