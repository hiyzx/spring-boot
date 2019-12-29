package org.zero.rocketmq;

import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author yezhaoxing
 * @date 2019/12/29
 */
@RestController
public class HelloController {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @GetMapping("/hello")
    public String hello(){
        // send message synchronously
        // rocketMQTemplate.convertAndSend("zero-topic", "Hello, World!");
        //send spring message
        // rocketMQTemplate.send("zero-topic", MessageBuilder.withPayload("Hello, World! I'm from spring message").build());
        //send messgae asynchronously
        rocketMQTemplate.asyncSend("zero-topic-2", new OrderPaidEvent("T_001", new BigDecimal("88.00")), new SendCallback() {
            @Override
            public void onSuccess(SendResult var1) {
                System.out.printf("async onSucess SendResult=%s %n", var1);
            }

            @Override
            public void onException(Throwable var1) {
                System.out.printf("async onException Throwable=%s %n", var1);
            }

        });
        //Send messages orderly
        // rocketMQTemplate.syncSendOrderly("zero_orderly_topic",MessageBuilder.withPayload("Hello, World").build(),"hashkey");

        //rocketMQTemplate.destroy(); // notes:  once rocketMQTemplate be destroyed, you can not send any message again with this rocketMQTemplat
        return "success";
    }
}

