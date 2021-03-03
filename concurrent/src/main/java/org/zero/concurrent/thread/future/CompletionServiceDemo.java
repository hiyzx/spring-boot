package org.zero.concurrent.thread.future;

import org.zero.concurrent.Log;

import java.util.concurrent.*;

/**
 * @author 水寒
 * @date 2021/3/3
 */
public class CompletionServiceDemo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        CompletionService<Integer> cs = new ExecutorCompletionService<>(executorService);

        cs.submit(() -> {
            Log.log("执行操作1");
            TimeUnit.SECONDS.sleep(1);
            return 1;
        });

        cs.submit(() -> {
            Log.log("执行操作2");
            TimeUnit.SECONDS.sleep(3);
            return 3;
        });

        cs.submit(() -> {
            Log.log("执行操作3");
            TimeUnit.SECONDS.sleep(2);
            return 2;
        });
        for (int i = 0; i < 3; i++) {
            Integer result = cs.take().get();
            Log.log("result = " + result);
        }

    }
}
