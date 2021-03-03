package org.zero.concurrent.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 水寒
 * @date 2021/2/24
 * @description 活锁
 */
public class LiveLock {

    class Account {
        private int balance;

        private final Lock lock = new ReentrantLock();

        /**
         *  在并发条件下,可能形成活锁, A线程获取到this.lock后, 获取不到tar.lock, 并释放锁
         *  B线程获取到tar.lock后, 获取不到this.lock, 并释放锁, 两个线程互相谦让都无法执行.
         *  解决方案: 1. 重试需要随机时间
         *          2. 统一加锁顺序
         *
         */
        void transfer(Account tar, int amount) {
            while (true) {
                if (this.lock.tryLock()) {
                    try {
                        if (tar.lock.tryLock()) {
                            try {
                                this.balance -= amount;
                                tar.balance += amount;
                                break;
                            } finally {
                                tar.lock.unlock();
                            }
                        }
                    } finally {
                        this.lock.unlock();
                    }
                }
            }
        }
    }
}
