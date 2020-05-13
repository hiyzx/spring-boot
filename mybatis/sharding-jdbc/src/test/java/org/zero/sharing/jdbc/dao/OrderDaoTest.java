package org.zero.sharing.jdbc.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 水寒
 * @date 2020/5/13
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class OrderDaoTest {

    private static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(10);

    @Autowired
    private OrderDao orderDao;

    @Test
    public void insertOrder() {
        for (int i = 0; i < 4; i++) {
            orderDao.insertOrder(new BigDecimal(10), (long)i, false);
        }

    }

    @Test
    public void selectOrderByIds() {
        List<Long> list = new ArrayList<>(Arrays.asList(1L, 2L));
        List<Map> maps = orderDao.selectOrderByIds(list);
    }
}
