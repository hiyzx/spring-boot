package org.zero.activemq.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;
import javax.jms.TextMessage;

@Slf4j
@Component
public class MessageHandler {

    @JmsListener(destination = MQConfig.QUEUE)
    public void onMessage(Message message) {
        try {
            String messageText = ((TextMessage) message).getText();
            log.info(messageText);
        } catch (Exception e) {
            log.info(e.getMessage(), e);
        }
    }
}
