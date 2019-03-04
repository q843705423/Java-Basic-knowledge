package com.company.thread_003;

public class T {
    private int count = 10;
    public synchronized void m(){//等同于在方法内执行 synchronized(this)
        //所以这里锁定的是当前的对象，而不是锁定这段代码块
        System.out.println(Thread.currentThread().getName()+",count:"+count);
    }
}
