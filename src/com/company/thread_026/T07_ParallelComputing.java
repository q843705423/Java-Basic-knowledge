package com.company.thread_026;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 并行计算
 *
 */
public class T07_ParallelComputing {
    public static void main(String []args) throws ExecutionException, InterruptedException {
        long startTime = System.currentTimeMillis();
        List<Integer> list = getResultInRange(0, 200000);
//        list.forEach(System.out::println);
        long endTime = System.currentTimeMillis();
        System.out.println("spend time:"+(endTime-startTime));
        //spend time:4537
        startTime = System.currentTimeMillis();
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        Future<List<Integer>> a = threadPool.submit(new MyTask(1,100000));
        Future<List<Integer>> b = threadPool.submit(new MyTask(100001,150000));
        Future<List<Integer>> c = threadPool.submit(new MyTask(150001,180000));
        Future<List<Integer>> d = threadPool.submit(new MyTask(180001,200000));
        List<Integer>all = new ArrayList<>();
        //阻塞在这里
        all.addAll(a.get());
        all.addAll(b.get());
        all.addAll(c.get());
        all.addAll(d.get());
        endTime = System.currentTimeMillis();
        System.out.println("spend time:"+(endTime - startTime));
    }
    static boolean isPrime(int number){
        if(number == 1 || number == 2){
            return true;
        }
        for(int i= 2;i<number;i++){
            if(number % i == 0 ){
                return false;
            }
        }
        return true;
    }
    static List<Integer> getResultInRange(int begin,int end){
        end = end + 1;
        List<Integer> list = new ArrayList<>();
        for(int i = begin ; i < end ; i ++ ){
            if(isPrime(i)){
                list.add(i);
            }
        }
        return list;
    }
static class MyTask implements Callable<List<Integer>> {
        private int first;
        private int end;
    MyTask(int begin,int end){
        first = begin;
        this.end = end;
    }
    @Override
    public List<Integer> call() {
        return getResultInRange(first,end);
    }
}
}

