package org.zero.rocketmq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.stream.LongStream;

import static org.zero.rocketmq.config.SystemConstant.*;

/**
 * @author yezhaoxing
 * @date 2019/12/29
 */
@RestController
@Slf4j
public class HelloController {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @GetMapping("/convertAndSend")
    public String convertAndSend() {
        // 发送普通消息(如果使用tag选择器,destination的字段为====topicName:tagName )
        LongStream.range(0, 20).parallel()
            .forEach(l -> rocketMQTemplate.convertAndSend(GENERAL_TOPIC, "Hello, World!"));
        /*rocketMQTemplate.send("zero-topic",
            MessageBuilder.withPayload("Hello, World! I'm from spring message").build());*/
        return "success";
    }

    @GetMapping("/sendBroadcast")
    public String sendBroadcast() {
        // 发送普通消息(如果使用tag选择器,destination的字段为====topic:tag )
        rocketMQTemplate.convertAndSend(GENERAL_TOPIC_BROADCAST, "Hello, World!");
        return "success";
    }

    @GetMapping("/sendDelay")
    public String sendDelay() {
        // 发送延迟消息
        rocketMQTemplate.syncSend(DELAY_TOPIC,
            MessageBuilder.withPayload("Hello, World! I'm from delay message").build(),
            rocketMQTemplate.getProducer().getSendMsgTimeout(), 2);
        return "success";
    }

    @GetMapping("/asyncSend")
    public String asyncSend() {
        // 发送异步消息
        rocketMQTemplate.asyncSend(ASYNC_TOPIC, new OrderPaidEvent("T_001", new BigDecimal("88.00")),
            new SendCallback() {
                @Override
                public void onSuccess(SendResult var1) {
                    System.out.printf("async onSuccess SendResult=%s %n", var1);
                }

                @Override
                public void onException(Throwable var1) {
                    System.out.printf("async onException Throwable=%s %n", var1);
                }

            });
        return "success";
    }

    @GetMapping("/syncSendOrderly")
    public String syncSendOrderly() {
        // 发送顺序消息(对hashkey选择队列,同一个队列的消息顺序消费)
        LongStream.range(1, 10).parallel().forEach(l -> {
            for (int i = 0; i < 3; i++) {
                rocketMQTemplate.syncSendOrderly(ORDERLY_TOPIC, MessageBuilder.withPayload(l + "   " + i).build(),
                    String.valueOf(l));
            }
        });
        return "success";
    }

    @GetMapping("/sendMessageInTransaction")
    public String sendMessageInTransaction() {
        // 发送事务消息
        Message<String> msg = MessageBuilder.withPayload("Hello, World!").build();
        rocketMQTemplate.sendMessageInTransaction(TRANSACTION_TOPIC, TRANSACTION_TOPIC, msg, null);
        return "success";
    }
}
