package org.zero.concurrent.sync;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 水寒
 * @date 2021/2/18
 */
public class 一次性获取所有的资源 {

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
            // 一次性申请转出账户和转入账户，直到成功
            while (actr.apply(this, target)) {
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
    }

    // 定义一个管理员, 一次性获取所有的资源
    class Allocator {
        private List<Object> als = new ArrayList<>();

        // 一次性申请所有资源
        synchronized boolean apply(Object from, Object to) {
            if (als.contains(from) || als.contains(to)) {
                return false;
            } else {
                als.add(from);
                als.add(to);
            }
            return true;
        }

        // 一次性释放所有资源
        synchronized void free(Object from, Object to) {
            als.remove(from);
            als.remove(to);
        }
    }
}
