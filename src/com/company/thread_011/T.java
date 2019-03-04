package com.company.thread_011;

import java.util.concurrent.TimeUnit;

/**
 * 在程序执行过程中，如果发生了异常，那么默认情况下，锁是一定会释放的
 * 所以在并发处理的时候， 要对异常的代码非常的小心， 不然可能会发生不一致的地方
 * 比如, 在并发处理得到过程中，多个 servlet 线程共同访问同一个资源，如果异常处理的不合适
 * 在第一个线程出现锁了之后，其他线程就会进入到同步代码块，可能会访问到异常的数据
 * 因此要非常小心的处理同步业务里的异常
 */
public class T {
    int count = 0;
    synchronized void m(){
        System.out.println(Thread.currentThread().getName()+" start");
        while (true){
            count ++;
            System.out.println(Thread.currentThread().getName()+" count:"+count);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (count == 5){
                int z = 3 / 0;
                //当到5的时候，会抛出异常,锁被释放了
                // synchronized
                //如果你不想锁释放，会发生
            }
        }
    }
    public static void main(String []args){
        T t=  new T();
        Runnable runnable = t::m;
        new Thread(runnable,"t1").start();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(runnable,"t2").start();

    }
}
