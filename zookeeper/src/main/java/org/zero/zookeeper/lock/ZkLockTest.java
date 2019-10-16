package org.zero.zookeeper.lock;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * zookeeper实现分布式锁 利用节点名称唯一性
 */
public class ZkLockTest {

    private String zkHost = "localhost:2181";

    private String lockNameSpace = "/myLock";

    private String nodeString = lockNameSpace + "/test";

    private ZooKeeper zk;

    private ZkLockTest() {
        try {
            zk = new ZooKeeper(zkHost, 60000, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    System.out.println("Receive event " + watchedEvent);
                    if (Event.KeeperState.SyncConnected == watchedEvent.getState())
                        System.out.println("connection is established...");
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void ensureRootPath() throws InterruptedException {
        try {
            if (zk.exists(lockNameSpace, true) == null) {
                zk.create(lockNameSpace, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

    private void watchNode(String nodeString, final Thread thread) throws InterruptedException {
        try {
            zk.exists(nodeString, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    System.out.println("==" + watchedEvent.toString());
                    if (watchedEvent.getType() == Event.EventType.NodeDeleted) {
                        System.out.println("There is a Thread released Lock==============");
                        thread.interrupt();
                    }
                }

            });
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取锁
     */
    private void lock() throws InterruptedException {
        String path = null;
        ensureRootPath();
        watchNode(nodeString, Thread.currentThread());
        while (true) {
            try {
                path = zk.create(nodeString, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            } catch (KeeperException e) {
                System.out.println(Thread.currentThread().getName() + "  getting Lock but can not get");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                    System.out.println("thread is notify");
                }
            }
            if (path != null && !path.trim().isEmpty()) {
                System.out.println(Thread.currentThread().getName() + "  get Lock...");
                return;
            }
        }
    }

    /**
     * 释放锁
     */
    private void unlock() {
        try {
            zk.delete(nodeString, -1);
            System.out.println("Thread.currentThread().getName() +  release Lock...");
        } catch (InterruptedException | KeeperException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        ExecutorService service = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 4; i++) {
            service.execute(() -> {
                ZkLockTest test = new ZkLockTest();
                try {
                    test.lock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                test.unlock();
            });
        }
        service.shutdown();
    }
}