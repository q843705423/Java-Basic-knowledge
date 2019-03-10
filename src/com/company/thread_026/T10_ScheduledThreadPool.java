package com.company.thread_026;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 以一定时间的执行某一个线程
 * 里面的线程是可以复用的
 */
public class T10_ScheduledThreadPool {
    public static void main(String []args){
        ScheduledExecutorService service = Executors.newScheduledThreadPool(4);
        //以固定的频率执行某个任务
        service.scheduleAtFixedRate(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
        },0, 500, TimeUnit.MILLISECONDS);
    }
}
