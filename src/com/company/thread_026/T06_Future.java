package com.company.thread_026;

import java.util.concurrent.*;

public class T06_Future {
    public static void main(String []args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> task = new FutureTask<>(() -> {
            TimeUnit.SECONDS.sleep(3);
            return 1000;
        });//可以用
        new Thread(task).start();
        //阻塞线程进行
        System.out.println(task.get());

        ExecutorService service = Executors.newFixedThreadPool(5);
        Future<Integer>futureValue = service.submit(()->{

            return 1;
        });
        System.out.println(futureValue.isDone());
        System.out.println(futureValue.get());
        System.out.println(futureValue.isDone());
    }
}
