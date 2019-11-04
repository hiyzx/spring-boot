package org.zero.zookeeper.lock;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * zookeeper实现分布式锁 利用有序节点(避免羊群效应)
 */
public class ZkFairLockTest {

    private String zkHost = "localhost:2181";

    private String lockName = "/mylock";

    private String lockZnode = null;

    private ZooKeeper zk;

    private ZkFairLockTest() {
        try {
            zk = new ZooKeeper(zkHost, 6000, new Watcher() {
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

    private void ensureRootPath() {
        try {
            if (zk.exists(lockName, true) == null) {
                zk.create(lockName, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取锁
     */
    private void lock() {
        String path = null;
        ensureRootPath();
        try {
            path = zk.create(lockName + "/mylock_", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL_SEQUENTIAL);
            lockZnode = path;
            List<String> minPath = zk.getChildren(lockName, false);
            Collections.sort(minPath);
            System.out.println(Thread.currentThread().getName() + " 最小节点:" + minPath.get(0) + " ,当前节点:" + path);

            if (!this.nullToEmpty(path).trim().isEmpty() && !this.nullToEmpty(minPath.get(0)).trim().isEmpty()
                && path.equals(lockName + "/" + minPath.get(0))) {
                System.out.println(Thread.currentThread().getName() + "  get Lock...");
                return;
            }
            String watchNode = null;
            for (int i = minPath.size() - 1; i >= 0; i--) {
                if (minPath.get(i).compareTo(path.substring(path.lastIndexOf("/") + 1)) < 0) {
                    watchNode = minPath.get(i);
                    System.out.println(Thread.currentThread().getName() + " 当前节点:" + path + " ,监听节点:" + watchNode);
                    break;
                }
            }

            if (watchNode != null) {
                final Thread thread = Thread.currentThread();
                Stat stat = zk.exists(lockName + "/" + watchNode, new Watcher() {
                    @Override
                    public void process(WatchedEvent watchedEvent) {
                        if (watchedEvent.getType() == Event.EventType.NodeDeleted) {
                            thread.interrupt();
                        }
                    }

                });
                if (stat != null) {
                    System.out.println(
                        Thread.currentThread().getName() + " waiting for " + lockName + "/" + watchNode);
                }
            }
            try {
                Thread.sleep(1000000000);
            } catch (InterruptedException ex) {
                System.out.println(Thread.currentThread().getName() + " notify");
                System.out.println(Thread.currentThread().getName() + "  get Lock...");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String nullToEmpty(String path) {
        return path == null ? "" : path;
    }

    /**
     * 释放锁
     */
    private void unlock() {
        try {
            System.out.println(Thread.currentThread().getName() + " release Lock...");
            zk.delete(lockZnode, -1);
        } catch (InterruptedException | KeeperException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        ExecutorService service = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 4; i++) {
            service.execute(() -> {
                ZkFairLockTest test = new ZkFairLockTest();
                try {
                    test.lock();
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                test.unlock();
            });
        }
        service.shutdown();
    }

}