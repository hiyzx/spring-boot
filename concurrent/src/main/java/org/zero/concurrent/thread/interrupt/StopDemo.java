package org.zero.concurrent.thread.interrupt;

import org.zero.concurrent.Log;

/**
 * @author yezhaoxing
 * @date 2020/1/13
 */
public class StopDemo implements Runnable {

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            Log.log("进入线程");
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Log.log("线程中断");// 不会执行
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new StopDemo());
        thread.start();
        Thread.sleep(2000L);
        // 废弃方法,因为一调用,线程就立刻停止
        thread.stop();
    }
}
