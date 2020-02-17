package org.zero.concurrent.thread.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author yezhaoxing
 * @date 2020/1/14
 */
public class ThreadLocalMain {

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    Date date = new Date();
                    try {
                        Thread.sleep(new Random().nextInt(1000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(simpleDateFormat.format(date));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
