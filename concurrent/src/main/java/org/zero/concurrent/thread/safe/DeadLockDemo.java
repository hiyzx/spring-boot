package org.zero.concurrent.thread.safe;

import org.zero.concurrent.Log;

/**
 * @author yezhaoxing
 * @date 2020/1/13
 */
public class DeadLockDemo {

    private static final Object objA = new Object();
    private static final Object objB = new Object();

    // 双方都在等待对方释放的锁
    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (objA) {
                Log.log("A进入");
                synchronized (objB) {
                    Log.log("A 抓住了 B");
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (objB) {
                Log.log("B进入");
                synchronized (objA) {
                    Log.log("B 抓住了 A");
                }
            }
        }).start();
    }
}
