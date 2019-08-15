package org.zero.entertainment.util;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yezhaoxing
 * @date 2019/8/8
 */
@Slf4j
public class DateUtil {

    /**
     * 锁对象
     */
    private static final Object lockObj = new Object();

    /**
     * 存放不同的日期模板格式的sdf的Map
     */
    private static Map<String, ThreadLocal<SimpleDateFormat>> sdfMap = new HashMap<>();

    /**
     * 返回一个ThreadLocal的sdf,每个线程只会new一次sdf
     *
     * @param pattern
     * @return
     */
    public static SimpleDateFormat getSdf(final String pattern) {

        ThreadLocal<SimpleDateFormat> tl = sdfMap.get(pattern);

        // 此处的双重判断和同步是为了防止sdfMap这个单例被多次put重复的sdf
        if (tl == null) {
            synchronized (lockObj) {
                tl = sdfMap.get(pattern);
                if (tl == null) {
                    System.out.println("-------------------------------------------------------------");
                    // 只有Map中还没有这个pattern的sdf才会生成新的sdf并放入map
                    System.out.println("put new sdf of pattern " + pattern + " to map");

                    // 这里是关键,使用ThreadLocal<SimpleDateFormat>替代原来直接new SimpleDateFormat
                    tl = ThreadLocal.withInitial(() -> {
                        log.info("thread: " + Thread.currentThread() + " init pattern: " + pattern);
                        return new SimpleDateFormat(pattern);
                    });
                    sdfMap.put(pattern, tl);
                }
            }
        }
        return tl.get();
    }
}
