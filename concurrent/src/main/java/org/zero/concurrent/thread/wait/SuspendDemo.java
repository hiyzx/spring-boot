package org.zero.concurrent.thread.wait;

import lombok.SneakyThrows;
import org.zero.concurrent.Log;

/**
 * @author yezhaoxing
 * @date 2020/1/13
 */
public class SuspendDemo implements Runnable {

    private static final Object object = new Object();

    @SneakyThrows
    @Override
    public void run() {
        synchronized (object) {
            Log.log("持有锁");
            // suspend方法并不会释放锁,而是将线程挂起,等待其他方法唤醒
            Thread.currentThread().suspend();
        }
        Log.log("释放锁");
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new SuspendDemo(), "thread1");
        thread1.start();
        Thread.sleep(1000);
        // 唤醒线程
        thread1.resume();

        Thread thread2 = new Thread(new SuspendDemo(), "thread2");
        thread2.start();
        Thread.sleep(1000);
        thread2.resume();
    }
}
