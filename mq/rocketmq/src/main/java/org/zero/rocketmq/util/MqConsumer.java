package org.zero.rocketmq.util;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import org.zero.rocketmq.OrderPaidEvent;

import static org.zero.rocketmq.config.SystemConstant.*;

/**
 * @author yezhaoxing
 * @date 2019/12/31
 */
@Slf4j
@Service
public class MqConsumer {

    @Service
    @RocketMQMessageListener(topic = GENERAL_TOPIC, consumerGroup = "general-consumer_test-topic")
    public class GeneralConsumer implements RocketMQListener<String> {

        @Override
        public void onMessage(String message) {
            log.info("received message: {}", message);
        }
    }

    // 设置广播消费
    @Service
    @RocketMQMessageListener(topic = GENERAL_TOPIC_BROADCAST, messageModel = MessageModel.BROADCASTING,
        consumerGroup = "broadcast-consumer_test-topic")
    public class BroadcastConsumer1 implements RocketMQListener<String> {

        @Override
        public void onMessage(String message) {
            log.info("1 received message: {}", message);
        }
    }

    @Service
    @RocketMQMessageListener(topic = DELAY_TOPIC, consumerGroup = "delay-consumer_test-topic")
    public class DelayConsumer implements RocketMQListener<String> {

        @Override
        public void onMessage(String message) {
            log.info("received message: {}", message);
        }
    }

    @Service
    @RocketMQMessageListener(topic = ASYNC_TOPIC, consumerGroup = "async-consumer_test-topic")
    public class AsyncConsumer implements RocketMQListener<OrderPaidEvent> {

        @Override
        public void onMessage(OrderPaidEvent message) {
            log.info("received message: {}", message);
        }
    }

    @Service
    @RocketMQMessageListener(topic = ORDERLY_TOPIC, consumerGroup = "orderly-consumer_test-topic")
    public class OrderlyConsumer implements RocketMQListener<String> {

        @Override
        public void onMessage(String message) {
            log.info("received message: {}", message);
        }
    }

    @Service
    @RocketMQMessageListener(topic = TRANSACTION_TOPIC, consumerGroup = "transaction-consumer_test-topic")
    public class TransactionConsumer implements RocketMQListener<String> {

        @Override
        public void onMessage(String message) {
            log.info("received message: {}", message);
        }
    }

    @RocketMQTransactionListener(txProducerGroup = TRANSACTION_TOPIC)
    class TransactionListenerImpl implements RocketMQLocalTransactionListener {
        @Override
        public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
            // 模拟本地事务
            log.info("执行本地事务逻辑");
            long time = RandomUtil.randomInt(10);
            RocketMQLocalTransactionState state;
            if (time % 3 == 0) {
                // 过一会再问
                state = RocketMQLocalTransactionState.UNKNOWN;
            } else if (time % 3 == 1) {
                // 回滚
                state = RocketMQLocalTransactionState.ROLLBACK;
            } else {
                // 提交
                state = RocketMQLocalTransactionState.COMMIT;
            }
            log.info("{} executeLocalTransaction : {}", msg.getHeaders().getId(), state);
            return state;
        }

        @Override
        public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
            // ... check transaction status and return bollback, commit or unknown
            log.info("消息回查");
            long time = RandomUtil.randomInt(10);
            RocketMQLocalTransactionState state;
            if (time % 3 == 0) {
                // 过一会再问
                state = RocketMQLocalTransactionState.UNKNOWN;
            } else if (time % 3 == 1) {
                // 回滚
                state = RocketMQLocalTransactionState.ROLLBACK;
            } else {
                // 提交
                state = RocketMQLocalTransactionState.COMMIT;
            }
            log.info("{} checkLocalTransaction : {}", msg.getHeaders().getId(), state);
            return state;
        }
    }
}
