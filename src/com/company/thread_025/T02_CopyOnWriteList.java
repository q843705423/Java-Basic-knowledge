package com.company.thread_025;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * 写实复制list
 * 可以让你不用让你不用加锁
 *
 *
 * 这个适用于
 * 写的很少,读的很多的情况
 *
 * 比如事件监听器
 *
 */
public class T02_CopyOnWriteList {
//    List<String>list = new ArrayList<>();
    public static void main(String []args){
//        List<String>list = new CopyOnWriteArrayList<>();
        List<String>list = new ArrayList<>();
        Random r = new Random();
        Thread[]threads = new Thread[100];
        Runnable task = ()->{
            for(int i = 0 ; i < 10000 ; i++){
                list.add(r.nextInt(200000)+"a");
            }
        };
        for(int i = 0 ; i < threads.length ; i ++){
            threads[i] = new Thread(task);
        }
        runAndComputerTime(threads);
        System.out.println(list.size());
    }

    private static void runAndComputerTime(Thread[] threads) {
        long s1 = System.currentTimeMillis();
        CountDownLatch latch = new CountDownLatch(threads.length);
        Arrays.asList(threads).forEach(thread -> {
            thread.start();
        });
        Arrays.asList(threads).forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        long s2 = System.currentTimeMillis();
        System.out.println(s2 - s1);
    }
}
