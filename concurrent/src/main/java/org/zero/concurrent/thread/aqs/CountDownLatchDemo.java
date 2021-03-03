package org.zero.concurrent.thread.aqs;

import org.zero.concurrent.Log;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author yezhaoxing
 * @date 2020/1/13
 */
public class CountDownLatchDemo {

	public static void main(String[] args) throws InterruptedException {
		Log.log("开始");
		CountDownLatch countDownLatch = new CountDownLatch(2);
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		executorService.execute(() -> {
			Log.log("线程1");
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			countDownLatch.countDown();
		});
		executorService.execute(() -> {
			Log.log("线程2");
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			countDownLatch.countDown();
		});
		countDownLatch.await();
		Log.log("结束");
	}
}
