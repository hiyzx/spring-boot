package org.zero.activemq.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Component
public class MessageHandler {

	private static ExecutorService executorService = Executors.newFixedThreadPool(10);

	@JmsListener(destination = MQConfig.QUEUE)
	public void onMessage(Message message) throws JMSException {
		String messageText = ((TextMessage) message).getText();
		log.info("接收请求: " + messageText);
		try {
			Thread.sleep(2000);
			log.info("消费: " + messageText);
		} catch (Exception ex) {
			log.info(ex.getMessage(), ex);
		}
	}
}
