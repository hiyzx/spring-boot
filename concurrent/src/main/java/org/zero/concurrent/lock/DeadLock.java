package org.zero.concurrent.lock;

/**
 * @author 水寒
 * @date 2021/2/24
 */
public class DeadLock {

    static class Account {
        int balance;

        /**
         * 在并发条件下,可能形成死锁, 解决方案: 1. 统一加锁顺序,一次性获取所有的资源
         * @see: 一次性获取所有的资源.java
         *
         */
        void transfer(Account tar, int amount) {
            synchronized (this) {
                synchronized (tar) {
                    this.balance -= amount;
                    tar.balance += amount;
                }
            }
        }
    }
}
