package org.zero.concurrent.thread.lock;

import org.zero.concurrent.Log;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yezhaoxing
 * @date 2020/2/10
 */
public class ReentrantLockDemo {

	private static final ReentrantLock LOCK = new ReentrantLock();
	private static final Condition CONDITION = LOCK.newCondition();
	private static int i = 1;

	public static void main(String[] args) throws InterruptedException {
		Thread thread1 = new Thread(new PrintThread(), "奇数");
		thread1.start();
		Thread thread2 = new Thread(new PrintThread(), "偶数");
		thread2.start();

		Thread.sleep(1000);
	}

	static class PrintThread implements Runnable {
		@Override
		public void run() {
			while (i <= 100){
				LOCK.lock();
				CONDITION.signal();
				try {
					Log.log(i);
					i++;
					if(i < 100){
						CONDITION.await();
					}
				} catch (Exception ex) {

				} finally {
					LOCK.unlock();
				}
			}
		}
	}
}
