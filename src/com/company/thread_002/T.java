package com.company.thread_002;

public class T {
    private int count =  10 ;

    public void m(){
        synchronized (this){
            // 这个锁的引用就是这个类的对象自身的引用
            // 有一个巨大的误区，synchronized 锁定的并不是代码块
            // 它锁定的是一个对象.
            // 注意，这样就一种更加简单一点的写法，那就是thread_003
            count --;
            System.out.println(Thread.currentThread().getName()+",count:"+count);
        }
    }
}
