package com.zero.activemq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageHandler {

   /* @JmsListener(destination = MqConstants.QUEUE, concurrency = "5")
    public void receiveQueue(String txtMsg) {
        User user = JsonUtil.readValue(txtMsg, User.class);
        System.out.println(user.getName());
    }*/

    @JmsListener(destination = MqConstants.QUEUE, containerFactory = "ptpContainer")
    public void receive(String msg){

            System.out.println("点对点模式1: " + msg);
        //抛出异常后,消息不会被消费成功,将会进行重试,达到次数后,进入死信队列
//        throw new RuntimeException();
    }
}
