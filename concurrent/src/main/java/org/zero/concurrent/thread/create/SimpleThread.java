package org.zero.concurrent.thread.create;

import org.zero.concurrent.Log;

/**
 * @author yezhaoxing
 * @date 2020/1/13
 */
public class SimpleThread {

    public static void main(String[] args){
        Thread thread = new Thread(() -> Log.log(Thread.currentThread().getName()));
        thread.start();
    }
}
