package com.zero.activemq;

import com.zero.po.User;
import com.zero.util.JsonUtil;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class MessageHandler {

    @Async
    @JmsListener(destination = MqConstants.QUEUE)
    public void receiveQueue(String txtMsg) {
        User user = JsonUtil.readValue(txtMsg, User.class);
        System.out.println(user.getName());
    }
}
