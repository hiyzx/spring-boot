package com.zero.activemq;

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
            Thread.sleep(5000);
            String messageText = ((TextMessage) message).getText();
            System.out.println(messageText);
        } catch (Exception e) {
            log.info(e.getMessage(), e);
        }
    }
}
