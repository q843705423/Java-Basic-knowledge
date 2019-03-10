package com.company.thread_020;

import java.util.concurrent.TimeUnit;

/**
 * jdk里面提供了一个新的锁
 * reentrantLock 他是用来替代 synchronized 的
 * 本例由于锁定m1，只有m1执行完了以后，m2才能执行
 * 这里复习的是synchronized的原始内容
 * 这是可重入锁
 */
public class ReentrantLock1 {
    synchronized  void m1(){
        for(int i = 0 ; i < 3 ; i ++){
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("m1");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    synchronized  void m2(){
        for(int i = 0 ; i < 3 ; i ++){
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("m2");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String []args){
        ReentrantLock1 reentrantLock1 = new ReentrantLock1();
        new Thread(reentrantLock1::m1).start();
        new Thread(reentrantLock1::m2).start();

    }
}
