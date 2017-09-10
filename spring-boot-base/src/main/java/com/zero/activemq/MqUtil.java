package com.zero.activemq;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.Queue;

/**
 * @author yezhaoxing
 * @date 2017/09/09
 */
@Component
public class MqUtil {

    @Resource
    private JmsTemplate jmsQueueTemplate;
    @Resource
    private Queue queue;

    public void sendToMQ(String msg) {
        jmsQueueTemplate.convertAndSend(queue, msg);
    }

}
