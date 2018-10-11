package org.zero.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.Future;

/**
 * @author yezhaoxing
 * @since 2018/09/29
 */
@Component
@Slf4j
public class AsyncTask {

    private static Random random = new Random();

    @Async
    public Future<String> task1() throws InterruptedException {
        log.info("开始做任务一");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        log.info("完成任务一，耗时：{} 毫秒", end - start);
        return new AsyncResult<>("完成任务");
    }

    @Async
    public Future<String> task2() throws InterruptedException {
        log.info("开始做任务二");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        log.info("完成任务一，耗时：{} 毫秒", end - start);
        return new AsyncResult<>("完成任务");
    }
}
