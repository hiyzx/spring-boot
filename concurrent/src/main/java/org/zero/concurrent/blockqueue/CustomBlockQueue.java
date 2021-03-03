package org.zero.concurrent.blockqueue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 水寒
 * @date 2021/2/24
 * @description 自定义阻塞队列
 */
public class CustomBlockQueue {

    private List<Integer> list = new ArrayList<>(10);
    private final Lock lock = new ReentrantLock();
    // 条件变量：队列不满
    private final Condition notFull = lock.newCondition();
    // 条件变量: 队列不为空
    private final Condition notEmpty = lock.newCondition();

    /**
     * 入队操作
     */
    void enq(Integer x) throws InterruptedException {
        lock.tryLock();
        try {
            while (list.size() == 10) {
                // 等待队列不满
                notFull.await();
            }
            list.add(x);
            // 入队后,通知可出队
            // signal唤醒任意一个线程竞争锁，signalAll唤醒同一个条件变量的所有线程竞争锁。但都只有一个线程获得锁执行。区别只是被唤醒线程的数量。
            // 所以用signalall可以避免极端情况线程只能等待超时
            notEmpty.signal();

        } finally {
            lock.unlock();
        }
    }

    /**
     * 出队操作
     */
    void deq() throws InterruptedException {
        lock.tryLock();
        try {
            while (list.size() == 0) {
                // 队列为空
                notEmpty.await();
            }
            list.remove(0);
            // 出队后,通知可入队
            notFull.signal();
        } finally {
            lock.unlock();
        }
    }
}
