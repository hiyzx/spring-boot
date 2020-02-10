package org.zero.concurrent.thread.lock;

import org.zero.concurrent.Log;

/**
 * @author yezhaoxing
 * @date 2020/2/10
 */
public class SynchronizedDemo {

	private static final Object obj = new Object();
	private static int i = 1;

	public static void main(String[] args) throws InterruptedException {
		Thread thread1 = new Thread(new PrintThread(), "奇数");
		thread1.start();
		Thread.sleep(100);
		Thread thread2 = new Thread(new PrintThread(), "偶数");
		thread2.start();

		Thread.sleep(1000);

		synchronized (obj){
			obj.notifyAll();
		}
	}

	static class PrintThread implements Runnable {
		@Override
		public void run() {
			while (i <= 100){
				synchronized (obj){
					obj.notify();
					Log.log(i);
					i ++;
					try {
						if(i < 100){
							obj.wait();
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
