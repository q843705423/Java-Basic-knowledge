package com.company.thread_024;

import java.util.Vector;

public class TicketSeller2 {
    /**
     * vector 里面的 remove方法是原子性 size也是原子性的
     *  但是由两个原子操作组成的部分不是原子性的
     */
    static Vector<String>tickets = new Vector<>();
    static {
        for( int i = 0 ; i < 1000 ; i ++){
            tickets.add("ticket" + i);
        }
    }
    public static void main(String []args){
        for( int i = 0 ; i < 10 ; i ++){
            new Thread(() -> {
               while (tickets.size() > 0){
                   System.out.println(Thread.currentThread().getName() + "sale:"+tickets.remove(0));
               }
            }).start();
        }
    }
}
