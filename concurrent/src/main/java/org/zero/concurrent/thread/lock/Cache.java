package org.zero.concurrent.thread.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author 水寒
 * @date 2021/2/25
 * @description 使用读写锁来构建缓存
 */
public class Cache<K, V> {

    private Map<K, V> map = new HashMap<>();
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private Lock readLock = readWriteLock.readLock();
    private Lock writeLock = readWriteLock.writeLock();

    public V get(K k) {
        V v = null;
        // 先获取读锁
        readLock.lock();
        try {
            v = map.get(k);
        } finally {
            readLock.unlock();
        }
        // 缓存存在直接返回
        if (v != null) {
            return v;
        }
        // 获取写锁
        writeLock.lock();
        try {
            // 再次get,可能其他线程已经获取了
            v = map.get(k);
            if(v == null){
                // 从数据库查询
                map.put(k, v);
            }
        } finally {
            writeLock.unlock();
        }
        return v;
    }
}
