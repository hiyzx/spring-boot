package org.zero.sharing.jdbc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zero.sharing.jdbc.dao.OrderDao;

import java.math.BigDecimal;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 水寒
 * @date 2020/5/13
 */
@RestController
public class OrderController {

    private static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(10);

    @Autowired
    private OrderDao orderDao;

    @GetMapping
    public void insert() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int j = 0; j < 10; j++) {
            EXECUTOR_SERVICE.execute(() -> {
                for (int i = 0; i < 10; i++) {
                    orderDao.insertOrder(new BigDecimal(10), (long)i, false);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
    }
}
