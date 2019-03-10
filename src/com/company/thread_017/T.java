package com.company.thread_017;

import java.util.concurrent.TimeUnit;

/**
 * 锁定某个对象o 时，若o的属性发生变化，不影响锁的使用
 * 它锁的不是栈内存里的引用，而是堆内存中的对象
 */
public class T {
    private Object o = new Object();
    public void  m(){
        synchronized (o){
            while (true){
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("name:"+Thread.currentThread().getName());
            }
        }


    }
    public static void main(String []args){
        T t = new T();
        new Thread(t::m, "t1").start();
        try {
            TimeUnit.SECONDS.sleep(3);
            System.out.println("ok");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread t2 = new Thread(t::m,"t2");

        t.o = "hello";
        t2.start();

    }
}
