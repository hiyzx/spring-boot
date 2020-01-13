package org.zero.concurrent;

import cn.hutool.core.date.DateUtil;

/**
 * @author yezhaoxing
 * @date 2020/1/13
 */
public class Log {

    public static void log(Object obj) {
        System.out.println(DateUtil.now() + " " + Thread.currentThread().getName() + " " + obj.toString());
    }
}
