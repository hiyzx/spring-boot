package org.zero.concurrent.sync;

/**
 * @author yezhaoxing
 * @date 2020/1/14
 */
public class SynchronizedMain {

    private int count = 0;

    public void add(){
        synchronized(this){
            count ++;
        }
    }

    private void mu(){
        synchronized(this){
            count ++;
        }
    }

    public synchronized void reduce(){
        count --;
    }

    public static void main(String[] args){
        SynchronizedMain tmp = new  SynchronizedMain();
        tmp.add();
        tmp.reduce();
        tmp.mu();
    }
}
