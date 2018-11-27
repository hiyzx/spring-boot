package org.zero.task;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;
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
    public void task() throws InterruptedException, ExecutionException {
        long startTime = System.currentTimeMillis();
        Future<String> task1 = asyncTask.task1();
        Future<String> task2 = asyncTask.task2();
        String task1Str = task1.get();
        String task2Str = task2.get();
        log.info("完成任务,耗时:{}", System.currentTimeMillis() - startTime);
    }
}