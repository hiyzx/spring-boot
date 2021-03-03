package org.zero.concurrent.thread.pool;

import org.zero.concurrent.Log;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author 水寒
 * @date 2021/3/2
 */
public class ThreadPoolMain {

    /**
     * 2021-03-02 21:46:08 pool-1-thread-1 执行0
     * 2021-03-02 21:46:08 pool-1-thread-3 执行4
     * 2021-03-02 21:46:08 pool-1-thread-2 执行3
     * 2021-03-02 21:46:11 pool-1-thread-2 执行1
     * 2021-03-02 21:46:11 pool-1-thread-1 执行2
     *
     * 核心线程 1, 最大线程3 ,队列容量2
     * 所以先打印3个, 最后打印2个
     */
    public static void main(String[] args){
        ThreadPoolExecutor executorService = new ThreadPoolExecutor(1, 3, 10, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(2));
        for (int i = 0; i < 10; i++) {
            int a = i;
            try {
                executorService.execute(() -> {
                    try {
                        Log.log("执行" + a);
                        TimeUnit.SECONDS.sleep(3);
                    } catch (Exception ex) {

                    }
                });
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    }
}
