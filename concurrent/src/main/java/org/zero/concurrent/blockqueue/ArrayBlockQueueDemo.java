package org.zero.concurrent.blockqueue;

import org.zero.concurrent.Log;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author 水寒
 * @date 2020/12/30
 */
public class ArrayBlockQueueDemo {

    private static final ArrayBlockingQueue<Integer> QUEUE = new ArrayBlockingQueue<>(5);
    private static final ExecutorService executorService = Executors.newFixedThreadPool(4);

    public static void main(String[] args) {
        Log.log("开始执行");
        executorService.execute(new Producer(QUEUE));
        executorService.execute(new Consumer(QUEUE));
    }
}

class Producer implements Runnable {

    private ArrayBlockingQueue<Integer> blockingQueue;

    public Producer(ArrayBlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                blockingQueue.put(i);
                Log.log("生产了数据 " + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Consumer implements Runnable {

    private ArrayBlockingQueue<Integer> blockingQueue;

    public Consumer(ArrayBlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
                Log.log("队列长度 " + blockingQueue.size());
                Integer take = blockingQueue.take();
                Log.log("消费了数据 " + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
