package org.zero.concurrent.thread.aqs;

import java.util.concurrent.CountDownLatch;

/**
 * @author yezhaoxing
 * @date 2020/1/13
 */
public class CountDownLatchDemo {

	private static CountDownLatch countDownLatch = new CountDownLatch(10);

	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 10; i++) {
			new Thread(() -> {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				countDownLatch.countDown();
				System.out.println(countDownLatch.getCount());
			}).start();
		}
		countDownLatch.await();
		System.out.println("执行完成");
	}
}
