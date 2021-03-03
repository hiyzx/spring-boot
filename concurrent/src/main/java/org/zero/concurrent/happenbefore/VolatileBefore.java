package org.zero.concurrent.happenbefore;

import java.util.concurrent.TimeUnit;

/**
 * @author 水寒
 * @date 2021/2/7
 */
public class VolatileBefore {

    public static void main(String[] args) {
        VolatileExample example = new VolatileExample();
        new Thread(example::reader).start();
        new Thread(example::writer).start();
    }

    /**
     * 这里有三种类型的happen-before
     * 1. 顺序性: 在同一个线程内, 25行代码必定先于26行代码
     * 2. volatile原则: volatile写必定先于读(ReentrantLock利用到了这个原则,内容有volatile修饰的state字段)
     * 3. 传递性: A 先于 B, B 先于 C, A必行先于 C
     */
    static class VolatileExample {
        int x = 0;
        volatile boolean v = false;

        public void writer() {
            x = 42;
            v = true;
        }

        public void reader() {
            try {
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (Exception ex) {

            }
            if (v) {
                System.out.println(x);
            } else {
                System.out.println("v = " + v);
            }
        }
    }
}
