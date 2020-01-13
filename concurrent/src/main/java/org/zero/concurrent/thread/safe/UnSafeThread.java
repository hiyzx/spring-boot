package org.zero.concurrent.thread.safe;

import org.zero.concurrent.Log;

import java.util.concurrent.CountDownLatch;

/**
 * @author yezhaoxing
 * @date 2020/1/13
 */
public class UnSafeThread {

    private static int num = 0;

    private static CountDownLatch countDownLatch = new CountDownLatch(10);

    private static synchronized void add() {
        num++;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10; j++) {
                    add();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                countDownLatch.countDown();
            }).start();
        }
        while (true){
            if(countDownLatch.getCount() == 0){
                Log.log(num);
                break;
            }
        }
    }
}
