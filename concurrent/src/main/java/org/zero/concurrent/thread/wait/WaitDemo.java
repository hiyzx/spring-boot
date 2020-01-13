package org.zero.concurrent.thread.wait;

import org.zero.concurrent.Log;

/**
 * @author yezhaoxing
 * @date 2020/1/13
 */
public class WaitDemo implements Runnable {

    private static final Object obj = new Object();

    @Override
    public void run() {
        synchronized (obj) {
            Log.log("占用资源");
            try {
                obj.wait();
                Log.log("被唤醒");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.log("释放资源");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new WaitDemo(), "thread1");
        thread1.start();

        Thread thread2 = new Thread(new WaitDemo(), "thread2");
        thread2.start();
        Thread.sleep(1000L);
        // 必须先休眠一会,否则可能还没有进行wait,就执行了notify
        synchronized (obj){
            // obj.notify(); // 只唤醒一个线程往下执行
            obj.notifyAll(); // 唤醒所有的线程往下执行
        }
    }
}
