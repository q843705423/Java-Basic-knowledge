package com.company.thread_024;

import java.util.ArrayList;
import java.util.List;
//卖票的小程序
public class TickerSeller1 {
    static List<String> tickets = new ArrayList<>();
    static {
        for(int i = 0 ; i < 1000 ; i ++){
            tickets.add("ticket" + i );
        }
    }
    public static void main(String []args){
        for(int i = 0 ; i < 10 ; i ++){
            new Thread(()->{
                while ( tickets.size() > 0 ){
                    System.out.println(Thread.currentThread().getName() + "sale:"+tickets.remove(0));
                }
            },"t" + (i+1)).start();
        }

    }
}
