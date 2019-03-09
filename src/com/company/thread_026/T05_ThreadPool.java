package com.company.thread_026;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class T05_ThreadPool {
    public static void main(String []args){
        //
        ExecutorService service = Executors.newFixedThreadPool(5);
        for(int i = 0 ; i < 6 ; i++){
            service.execute(()->{
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            });
        }
        System.out.println(service);
        System.out.println(service.isTerminated());
        System.out.println(service.isShutdown());
        System.out.println(service);
        service.isTerminated();

    }
}
