package com.company.thread_learn;

public class ThreadTest {
    static volatile int cnt = 0;
    public static void  main(String...args){
        Thread t1 = new Thread(() -> {
            for(int k=0; k < 9 ; k ++){
                System.out.println("thread 1:"+cnt);
                cnt += 100;
            }
        });
        Thread t2 = new Thread(() -> {
            for(int k = 0 ; k < 9 ; k ++){
                System.out.println("thread 2:"+cnt);
                cnt = cnt+1;
            }
        });
        t1.start();
        t2.start();
    }
}
