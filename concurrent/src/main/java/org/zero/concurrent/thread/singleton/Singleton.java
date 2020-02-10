package org.zero.concurrent.thread.singleton;

/**
 * @author yezhaoxing
 * @date 2020/2/9
 */
public class Singleton {
	
	private volatile static Singleton instance;
	
	private Singleton(){
		
	}
	
	public static Singleton getInstance(){
		if(instance == null){
			synchronized (Singleton.class){
				if(instance == null){
					instance = new Singleton();
				}
			}
		}
		return instance;
	}
	
}
