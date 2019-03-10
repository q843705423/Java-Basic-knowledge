package com.company.thread_007;
//同步方法和非同步方法是否可以同时调用
public class T {
    public synchronized void  m1(){
        System.out.println(Thread.currentThread().getName()+" m1 start...");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" m1 end...");
    }

    /**
     * 因为不需要申请这把锁,所以同步方法和非同步方法是否可以同步
      */
    public void m2(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" m2");
    }
    public static void main(String []args){
        T t = new T();
        new Thread(t::m1,"t1").start();
        new Thread(t::m2,"t2").start();
    }
}
