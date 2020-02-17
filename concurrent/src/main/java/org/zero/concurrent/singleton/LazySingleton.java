package org.zero.concurrent.singleton;

/**
 * @author yezhaoxing
 * @date 2020/1/16
 */
public class LazySingleton {

    // volatile禁止指令重排
    private static volatile LazySingleton instance;

    // 私有化构造器
    private LazySingleton() {}

    public static LazySingleton getInstance() {
        if (instance == null) {
            synchronized (LazySingleton.class) {
                if (instance == null) {
                    // 1. 为instance分配内存空间
                    // 2. 初始化instance
                    // 3. 将instance指向分配的内存地址
                    instance = new LazySingleton();
                }
            }
        }
        return instance;
    }
}
