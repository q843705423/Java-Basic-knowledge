package com.company.thread_019;
//87:26

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 曾经有一道面试题
 实现一个容器，提供两个方法，add size
 写两个线程，线程1加10个元素到容器里

 线程2监控元素的个数
 当个数到5个时候，线程2给出提示，并且结束这个程序
 请问以下代码是否能够实现这个功能


 可能存在
 */
public class MyContainer1 {
    //List list = new ArrayList();
    volatile List list = new ArrayList();

    public void add(Object o){
        list.add(o);
    }
    public int size(){
        return list.size();
    }

    public static void main(String []args){
        MyContainer1 c = new MyContainer1();
        new Thread(()->{
            for(int i = 0 ; i < 10 ; i ++){

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                c.add(new Object());
                System.out.println("add " + i);
            }
        }).start();
        new Thread(()->{
            while (true){
                if(c.size()==5){
                    System.out.println("break!!!");
                    break;
                }
            }
        }).start();

    }
}
