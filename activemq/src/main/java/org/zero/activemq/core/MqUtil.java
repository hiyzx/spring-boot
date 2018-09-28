package org.zero.activemq.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.Queue;

/**
 * @author yezhaoxing
 * @date 2017/09/09
 */
@Component
@Slf4j
public class MqUtil {

    @Resource
    private JmsTemplate jmsTemplate;
    @Resource
    private Queue queue;

    public void sendToMQ(final String msg) {
        jmsTemplate.convertAndSend(queue, msg);
        log.info("send msg {} to MQ", msg);
    }
}
