package org.zero.concurrent.thread.future;

import org.zero.concurrent.Log;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @author 水寒
 * @date 2021/3/2
 */
public class Water {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> ft2= new FutureTask<>(new T2Task());
        FutureTask<String> ft1= new FutureTask<>(new T1Task(ft2));

        Thread T1 = new Thread(ft1);
        T1.start();
        Thread T2 = new Thread(ft2);
        T2.start();
        Log.log(ft1.get());
    }

    static class T1Task implements Callable<String> {

        FutureTask<String> ft2;

        public T1Task(FutureTask<String> ft2){
            this.ft2 = ft2;
        }

        @Override
        public String call() throws Exception {
            Log.log("洗水壶");
            TimeUnit.SECONDS.sleep(1);

            Log.log("烧水");
            TimeUnit.SECONDS.sleep(15);

            String tf = ft2.get();
            Log.log("T1: 拿到茶叶:"+tf);

            Log.log("泡茶");
            return "上茶";
        }
    }

    static class T2Task implements Callable<String> {

        @Override
        public String call() throws Exception {
            Log.log("洗茶壶");
            TimeUnit.SECONDS.sleep(1);

            Log.log("洗茶杯");
            TimeUnit.SECONDS.sleep(15);

            Log.log("拿茶叶");
            return "铁观音";
        }
    }
}
