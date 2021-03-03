package org.zero.concurrent.limit;

import org.zero.concurrent.Log;

/**
 * @author 水寒
 * @date 2020/12/26
 */
public class CounterLimit {

    public static void main(String[] args) {
        Counter counter = new Counter();
        for (int i = 0; i < 11; i++) {
            new Thread(() -> {
                boolean grant = counter.grant();
                if (grant) {
                    Log.log("通过");
                } else {
                    Log.log("限速");
                }
            }).start();
        }
    }
}

class Counter {

    public long timeStamp = getNowTime();
    public int reqCount = 0;
    public final int limit = 10; // 时间窗口内最大请求数
    public final long interval = 1000; // 时间窗口ms

    public boolean grant() {
        long now = getNowTime();
        if (now < timeStamp + interval) {
            // 在时间窗口内
            reqCount++;
            // 判断当前时间窗口内是否超过最大请求控制数
            return reqCount <= limit;
        } else {
            timeStamp = now;
            // 超时后重置
            reqCount = 1;
            return true;
        }
    }

    private static Long getNowTime() {
        return System.currentTimeMillis();
    }

}
