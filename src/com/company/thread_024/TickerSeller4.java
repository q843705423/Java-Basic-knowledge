package com.company.thread_024;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

public class TickerSeller4 {
    /**
     * 这是一个并发的列表容器
     */
    static Queue<String> tickets = new ConcurrentLinkedDeque<>();
    static {
        for(int i = 0 ; i < 1000 ; i ++){
            tickets.add("ticket" + (i+ 1));
        }
    }

    public static void main(String []args){
        for(int i = 0 ; i < 10 ; i++){
            new Thread(() -> {
                while(true){
                    //没有加锁，但是却不会有线程的问题!为什么？
                    //为什么判断和操作不是原子性，也不会出问题？
                    //并发容器
                    String s = tickets.poll();
                    if(s == null){
                        break;
                    }else {
                        System.out.println(Thread.currentThread().getName() + "sale:" + s);
                    }
                }
            },"t"+(i+1)).start();
        }
    }
}
