package org.zero.concurrent.limit;

import com.google.common.util.concurrent.RateLimiter;
import org.zero.concurrent.Log;

import java.util.concurrent.TimeUnit;

/**
 * @author 水寒
 * @date 2020/12/26
 */
public class GuavaDemo {

    public static void main(String[] args) {
        testSmoothwarmingUp();

    }

    public static void testSmoothBursty() {
        // 每秒产生两个令牌,限制每秒只会打印两个
        RateLimiter r = RateLimiter.create(2);
        while (true) {
            Log.log("获取token: " + r.acquire() + "s");
        }
    }

    public static void testSmoothBursty2() {
        // 每秒产生两个令牌,没有消耗的令牌会累积, 可以应对下次突发流量
        RateLimiter r = RateLimiter.create(2);
        Log.log("开始");
        while (true)
        {
            Log.log("获取token1: " + r.acquire(6) + "s");
            try {
                Thread.sleep(2000);
            } catch (Exception e) {}
            Log.log("获取token2: " + r.acquire() + "s");
            Log.log("获取token3: " + r.acquire() + "s");
            Log.log("获取token4: " + r.acquire() + "s");
            Log.log("获取token5: " + r.acquire() + "s");
            System.out.println("end");
        }
    }

    public static void testSmoothwarmingUp() {
        RateLimiter r = RateLimiter.create(2, 3, TimeUnit.SECONDS);
        while (true)
        {
            Log.log("获取token1: " + r.acquire() + "s");
            Log.log("获取token2: " + r.acquire() + "s");
            Log.log("获取token3: " + r.acquire() + "s");
            Log.log("获取token4: " + r.acquire() + "s");
            Log.log("获取token5: " + r.acquire() + "s");
            Log.log("end");
        }
    }
}
