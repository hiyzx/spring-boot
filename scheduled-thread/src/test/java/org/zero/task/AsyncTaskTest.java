package org.zero.task;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.concurrent.Future;

/**
 * @author yezhaoxing
 * @since 2018/09/29
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class AsyncTaskTest {

    @Resource
    private AsyncTask asyncTask;

    @Test
    public void task() throws InterruptedException {
        long startTime = System.currentTimeMillis();
        Future<String> task1 = asyncTask.task1();
        Future<String> task2 = asyncTask.task2();
        while (!task1.isDone() || !task2.isDone()) {
            // Thread.sleep(1000);
        }

        log.info("完成任务,耗时:{}", System.currentTimeMillis() - startTime);
    }
}