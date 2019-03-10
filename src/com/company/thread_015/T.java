package com.company.thread_015;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 
 
 */
public class T {
    /**
     AtomicInteger 等 类的方法都是具有原子性的...
    这些类都是在底层实现的原子性.
     请证明 AtomicXXX 的多个方法不具备原子性
    证明 AtomXXX 和 synchronized 更加有效
     AtomXXX 是否能保证程序可见性
     */
    AtomicInteger count= new AtomicInteger(0) ;
    void m(){
        for(int i = 0 ; i < 10000 ; i ++ ){
            if(count.get() < 1000){
                count.incrementAndGet();
            }
        }
    }
    public static void main(String []args){
        T t = new T();
        Runnable runnable =  t::m;
        List<Thread> threads = new ArrayList<>();
        for(int i = 0 ; i < 10 ; i ++ ){
             Thread thread = new Thread(runnable);
             threads.add(thread);
        }
        threads.forEach(Thread::start);
        threads.forEach(o->{
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }); 
        System.out.println(t.count.get());
    }
}
