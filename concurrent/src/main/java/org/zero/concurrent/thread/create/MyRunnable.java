package org.zero.concurrent.thread.create;

import org.zero.concurrent.Log;

/**
 * @author yezhaoxing
 * @date 2020/1/13
 */
public class MyRunnable implements Runnable {

    @Override
    public void run() {
        Log.log(Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new MyRunnable());
        thread.setName("myRunnable");
        thread.start();
    }
}
