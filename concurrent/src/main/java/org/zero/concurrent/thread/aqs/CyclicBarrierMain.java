package org.zero.concurrent.thread.aqs;

import org.zero.concurrent.Log;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author 水寒
 * @date 2021/3/3
 */
public class CyclicBarrierMain {

    /**
     * CyclicBarrier的计数器是可以循环利用的，而且具备自动重置的功能，一旦计数器减到0会自动重置到你设置的初始值。 除此之外，
     * CyclicBarrier 还可以设置回调函数。
     */
    public static void main(String[] args) {

        Executor executorService1 = Executors.newFixedThreadPool(1);
        Executor executorService2 = Executors.newFixedThreadPool(2);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2, () -> {
            // 只有在此方法被调用之后,才会唤醒等待线程, 所以这里使用线程池处理
            // 执行回调线程是将cyclicBarrier内部计算器减到0的那个线程,在这里是b
            Log.log("调用回调函数");
            executorService1.execute(() -> {
                Log.log("执行计算");
                try {
                    TimeUnit.SECONDS.sleep(1);
                    Log.log("执行计算结束");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            Log.log("调用回调函数结束");
        });
        executorService2.execute(() -> {
            while (true) {
                Log.log("获取a");
                try {
                    TimeUnit.SECONDS.sleep(1);
                    cyclicBarrier.await();
                    Log.log("a阻塞结束");
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        executorService2.execute(() -> {
            while (true) {
                Log.log("获取b");
                try {
                    TimeUnit.SECONDS.sleep(2);
                    cyclicBarrier.await();
                    Log.log("b阻塞结束");
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
