package com.company.thread_020;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 特别注意，这个lock是手工锁，必须要手动释放
 * 一般在fin 里面把他解锁
 *
 * 它们有一些区别
 * 发生异常的时候，syn是自动释放锁的，而ReentrantLock是需要你在finally 里面自己手动释放的
 * ReentrantLock 是需要手动释放的，
 * reentrantLock可以进行尝试锁定，可以进行在一定时间内进行尝试锁定
 * 详细看ReentrantLock3
 */

public class ReentrantLook2 {
    Lock lock  = new ReentrantLock();
    void m1(){
        try {
            lock.lock();
            System.out.println("now is in m1");
            for(int i = 0 ; i < 5 ; i ++ ){
                System.out.println(i);
                TimeUnit.SECONDS.sleep(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    void m2(){
         try {
             System.out.println("start m2");
             lock.lock();
             System.out.println("locking m2");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public static void main(String []args){
        ReentrantLook2 reentrantLook2 = new ReentrantLook2();
        new Thread(reentrantLook2::m1).start();
        new Thread(reentrantLook2::m2).start();
    }
}
