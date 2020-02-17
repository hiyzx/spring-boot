package org.zero.concurrent.singleton;

/**
 * @author yezhaoxing
 * @date 2020/1/16
 */
public class HungrySingleton {

    // 饥饿式,一开始就实例化对象
    private static final HungrySingleton instance = new HungrySingleton();

    // 提供访问方法
    public static HungrySingleton getInstance(){
        return instance;
    }

    // 私有化构造器
    private HungrySingleton(){

    }
}
