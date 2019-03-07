package com.company.thread_020;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用 lockInterruptibly 代替 lock 可以使得主线程去打断这个线程
 */

public class ReentrantLock4 {
    public static void main(String []args){
        Lock lock = new ReentrantLock();
        new Thread(() -> {
            lock.lock();
            System.out.println("t1 start");
            try {
                TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
            System.out.println("t1 end");

        }).start();
        /**
         * 我们没有办法打断那个锁
         *
         */
        Thread t2 = new Thread(() -> {
            try {
                lock.lockInterruptibly();
                System.out.println("t2 start");
                TimeUnit.SECONDS.sleep(3);
                System.out.println("t2 end");
            } catch (InterruptedException e) {
                System.out.println("InterruptedException ");
            }finally {
                lock.unlock();
            }
        });
        t2.start();
        t2.interrupt();
    }
}
