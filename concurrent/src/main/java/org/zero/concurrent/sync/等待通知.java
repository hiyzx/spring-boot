package org.zero.concurrent.sync;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 水寒
 * @date 2021/2/18
 */
public class 等待通知 {

    class Account {
        // actr 应该为单例
        private Allocator actr;
        private int balance;

        public Account(Allocator actr, int balance) {
            this.actr = actr;
            this.balance = balance;
        }

        // 转账
        void transfer(Account target, int amt) {
            // 一次性申请转出账户和转入账户, 获取不到就wait
            actr.apply(this, target);
            try {
                // 锁定转出账户
                synchronized (this) {
                    // 锁定转入账户
                    synchronized (target) {
                        if (this.balance > amt) {
                            this.balance -= amt;
                            target.balance += amt;
                        }
                    }
                }
            } finally {
                actr.free(this, target);
            }
        }
    }

    // 定义一个管理员, 一次性获取所有的资源
    class Allocator {
        private List<Object> als = new ArrayList<>();

        // 一次性申请所有资源
        synchronized void apply(Object from, Object to){
            // 为true, 说明有人占有这两个资源.
            if (als.contains(from) || als.contains(to)) {
                try {
                    wait();
                    // 这里不持有则进入等待区.
                    // wait会释放所有锁而sleep不会释放锁资源.
                } catch (Exception ex) {

                }
            } else {
                als.add(from);
                als.add(to);
            }
        }

        // 一次性释放所有资源
        synchronized void free(Object from, Object to) {
            als.remove(from);
            als.remove(to);
            notifyAll();// 通知所有的线程去获取锁,但是只有一个线程会进入
        }
    }
}
