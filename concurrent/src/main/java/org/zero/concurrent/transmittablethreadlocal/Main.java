package org.zero.concurrent.transmittablethreadlocal;

import com.alibaba.ttl.TransmittableThreadLocal;
import org.zero.concurrent.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 水寒
 * @date 2021/2/24
 */
public class Main {

    private static ExecutorService executorService = Executors.newFixedThreadPool(5);
    private final static ThreadLocal<String> THREAD_LOCAL = ThreadLocal.withInitial(() -> "SUPER");
    private final static TransmittableThreadLocal<String> T_THREAD_LOCAL = new TransmittableThreadLocal<>();

    public static void main(String[] args) {
        THREAD_LOCAL.remove();
        THREAD_LOCAL.set("hahaha");
        // hahaha
        Log.log(THREAD_LOCAL.get());
        executorService.execute(() -> {
            // SUPER
            Log.log(THREAD_LOCAL.get());
        });

        T_THREAD_LOCAL.set("TTL_SUPER");
        Log.log(T_THREAD_LOCAL.get());
        executorService.execute(() -> {
            // SUPER
            Log.log(T_THREAD_LOCAL.get());
        });
    }
}
