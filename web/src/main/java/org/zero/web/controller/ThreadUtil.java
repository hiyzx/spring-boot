package org.zero.web.controller;

import lombok.Data;

/**
 * @author yezhaoxing
 * @date 2019/9/19
 */
@Data
public class ThreadUtil {

    ThreadLocal<String> threadLocal;

    public void set(){
        threadLocal = ThreadLocal.withInitial(() -> "hello");
    }
}
