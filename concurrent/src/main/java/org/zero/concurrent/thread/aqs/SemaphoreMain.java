package org.zero.concurrent.thread.aqs;

import org.zero.concurrent.Log;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author 水寒
 * @date 2020/12/13
 */
public class SemaphoreMain {

    public static void main(String[] args){
        Semaphore semaphore = new Semaphore(2);
        for (int i = 0; i < 5; i++) {
            new Task(semaphore, "线程名称_" + i).start();
        }
    }

    static class Task extends Thread{

        private Semaphore semaphore;

        public Task(Semaphore semaphore, String threadName){
            this.semaphore = semaphore;
            this.setName(threadName);
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();
                TimeUnit.SECONDS.sleep(10);
                Log.log("");
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
