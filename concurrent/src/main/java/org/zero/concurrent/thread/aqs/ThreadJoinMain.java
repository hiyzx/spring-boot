package org.zero.concurrent.thread.aqs;

import org.zero.concurrent.Log;

import java.util.concurrent.TimeUnit;

/**
 * @author 水寒
 * @date 2021/3/3
 */
public class ThreadJoinMain {

    /**
     * 利用线程退出后会调用join方法加入主线程, 但是在线程池就没有用, 线程池的线程不会退出
     *
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        Log.log("开始");
        Thread t1 = new Thread(() -> {
            Log.log("线程1");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        Thread t2 = new Thread(() -> {
            Log.log("线程1");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t2.start();
        t1.join();
        t2.join();
        Log.log("结束");
    }
}
