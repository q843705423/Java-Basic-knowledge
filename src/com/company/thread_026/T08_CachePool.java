package com.company.thread_026;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 使用 Cache_Pool 可以起了一个弹性的线程池
 * 当到固定秒数没有使用，将会销毁该线程对象
 *
 */
public class T08_CachePool {
    public static void main(String []args){
        ExecutorService service = Executors.newCachedThreadPool();
        for(int i = 0 ; i < 2 ; i ++){
            service.execute(() -> {
                System.out.println("hello world");

            });
        }
    }
}
