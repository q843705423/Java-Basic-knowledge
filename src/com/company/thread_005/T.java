package com.company.thread_005;

public class T implements Runnable{
    private int count = 10;

    /**
     * 一段 synchronized 的代码段是 原子操作
     * 是不可以分的
     */
    public synchronized void run(){
        count--;
        System.out.println(Thread.currentThread().getName()+",count:"+count);
    }
    public static void main(String []args){
        T t = new T();
        for (int i = 0; i < 5 ; i++){
            new Thread(t,"Thread" + i).start();

        }
    }
}
