package com.company.thread_019;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
    使用wait 和 notify 做到 wait会释放锁，
    而notify不会释放锁

 */
public class MyContainer3 {
        //List list = new ArrayList();
    volatile List list = new ArrayList();

    public void add(Object o){
        list.add(o);
    }
    public int size(){
        return list.size();
    }

    public static void main(String []args){
        MyContainer3 c = new MyContainer3();
        final Object lock = new Object();
        new Thread(()->{
            synchronized (lock){
                System.out.println("t2 is start");
                if(c.size() != 5){
                    try {
                        //使用wait，让访问的线程先等着,使得当前线程进入等待状态，并且释放锁
                        //当只有调用这个对象的notify方法的时候，就会启动等待线程
                        //notifyAll 就 唤醒 所有正在等待lock锁的
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("t2 is over");
            }
        }).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->{
            synchronized (lock){
                for(int i = 0 ; i < 10 ; i ++){
                    c.add(new Object());
                    System.out.println("add " + i);
                    if(c.size() ==5){
                        lock.notify();
                    }
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
//        new Thread(()->{
//            while (true){
//                if(c.size()==5){
//                    System.out.println("break!!!");
//                    break;
//                }
//            }
//        }).start();

    }
}
