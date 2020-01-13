package org.zero.concurrent.thread.create;

import org.zero.concurrent.Log;

/**
 * @author yezhaoxing
 * @date 2020/1/13
 */
public class MyThread extends Thread{

    @Override
    public void run() {
        Log.log(Thread.currentThread().getName());
    }

    public static void main(String[] args){
        Thread thread = new MyThread();
        thread.setName("myThread");
        thread.run(); // 还是用的主线程
        // thread.start();
    }
}
