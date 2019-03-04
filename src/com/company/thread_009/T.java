package com.company.thread_009;

import java.util.concurrent.TimeUnit;

/**
 * 一个同步方法可以调用另外一个同步方法,
 * 一个线程已经拥有了某个对象的锁，那么再次申请相同对象的时候，依然可以得到这个对象的锁
 *  换而言之，synchronized 获得的锁是可重入的
 */
public class T {
    synchronized void m1(){
        System.out.println("m1 start");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        m2();
    }

    private synchronized void m2() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m2");
    }
    public static void main(String []args){
        T t = new T();
        t.m1();
    }
}
