package org.zero.concurrent.thread.interrupt;

import org.zero.concurrent.Log;

/**
 * @author yezhaoxing
 * @date 2020/1/13
 */
public class FlagDemo implements Runnable {

    private static Boolean FLAG = Boolean.FALSE;

    @Override
    public void run() {
        while (!FLAG) {
            Log.log("进入线程");
        }
        Log.log("线程中断");
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new FlagDemo());
        thread.start();
        Thread.sleep(1000);
        FLAG = true;
    }
}
