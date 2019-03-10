package com.company.thread_022;

import java.util.concurrent.TimeUnit;

public class ThreadLocal1 {
    volatile static Person p  = new Person();
    public static void main(String []args){
        p.name = "Bob";
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(p.name);
        }).start();
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
                p.name = "Tom";
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
class Person{
    String name;
}
