package com.company.thread_020;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用Reentrantlock可以代替synchronized
 * 由于m1锁定this，只有m1执行完毕的时候，m2才可以执行
    ReentrantLock 还可以指定为公平锁
    竞争式的得到这把锁，  没有公平性可言
    synchronized 是 非公平锁
    ReentrantLock可以指定为公平锁
    ReentrantLock lock  = new ReentrantLock(true);
 *
 */
public class ReentrantLock5 {
    private static ReentrantLock lock = new ReentrantLock(true);
    public static void main(String []args){
        Runnable runnable = () -> {
            for(int i = 0 ; i < 100 ; i ++){
                lock.lock();
                System.out.println(Thread.currentThread().getName()+" get the lock");
                lock.unlock();
            }
        };
        new Thread(runnable,"t1").start();
        new Thread(runnable,"t2").start();
    }


}
