package org.zero.entertainment.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author yezhaoxing
 * @date 2019/8/5
 */
@Slf4j
public class DateTest {

    private static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);

    @Test
    public void test() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(100);
        for (int i = 0; i < 100; i++) {
            fixedThreadPool.execute(() -> {
                String date = "2019-08-08";
                SimpleDateFormat format = DateUtil.getSdf("yyyy-MM-dd");
                try {
                    Date parse = format.parse(date);
                    log.info(parse + "");
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
    }
}
