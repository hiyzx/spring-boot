package org.zero.concurrent.thread.create;

import org.zero.concurrent.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author yezhaoxing
 * @date 2020/1/13
 */
public class ThreadPool {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            Log.log(Thread.currentThread().getName());
        });
    }
}
