package com.company.thread_020;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * trylock 是尝试锁定，可以根据有没有拿到锁自己决定要做什么，
 * 以下有两种写法,一种是不传递参数,另一种是传递时间作为参数
 * ReentrantLock 比 syn 更加灵魂
 */
public class ReentrantLock3 {
    Lock lock = new ReentrantLock();
    void m2(){
       /* boolean locked = lock.tryLock();
        System.out.println("m2 lock result:"+locked);
        if(locked){
            lock.unlock();
            System.out.println("unlock success");
        }*/
       System.out.println("m2 start");
       boolean locked = false;
        try {
            locked = lock.tryLock(7,TimeUnit.SECONDS);
            System.out.println("locked:"+locked);
        } catch (InterruptedException e) {
            if(locked){
                lock.unlock();
                System.out.println("unlock");
            }
        }finally {
            lock.unlock();
            System.out.println("unlock");
        }
        System.out.println("m2 over");

    }
    void m1(){
        try{
            lock.lock();
            for(int i = 0 ; i < 5 ; i ++){
                System.out.println("now is " + i);
                TimeUnit.SECONDS.sleep(1);
            }
        }catch (Exception e){
           System.out.println("erorr");
        }finally {
           lock.unlock();
        }
    }
    public static void main(String []args){
        ReentrantLock3 reentrantLock3 = new ReentrantLock3();
        new Thread(reentrantLock3::m1).start();
        new Thread(reentrantLock3::m2).start();
    }
}
