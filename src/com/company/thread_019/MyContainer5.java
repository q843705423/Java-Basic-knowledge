package com.company.thread_019;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 不涉及同步，仅仅设计线程通讯的时候，使用 synchronized + wait/notify 就显得太重了
 *
 *   这时候应该考虑CountdownLatch/CyclicBarrier/Semaphore
 */
public class MyContainer5 {
    private volatile List<Object>list = new ArrayList<>();
    public void add(Object o){
        list.add(o);
    }
    public int size(){
        return list.size();
    }
    public static void main(String []args){
        MyContainer5 c = new MyContainer5();
        //往下数的门闩,调用countDown方法，就会把里面的数往下减1,使得门闩进入等待，就锁在这里
        CountDownLatch latch = new CountDownLatch(1);//使用门闩
        new Thread(()->{
            System.out.println("t2 is start");
            if(c.size()!=5){
                try {
                    latch.await();
                    //也可以指定等待时间
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("t2 is end");
        },"t2").start();
        new Thread(() -> {
            System.out.println("t1 is start");
            for(int i = 0 ; i < 10 ; i++){
                c.add(new Object());
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("add " + i);
                if(c.size() == 5){
                    latch.countDown();
                }
            }

            System.out.println("t1 is end");
        },"t1").start();
    }
}
