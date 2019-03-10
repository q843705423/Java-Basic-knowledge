package com.company.thread_026;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class T122_ForkJoinPool {
    public static long CALCULATE_NUMBER = 10000000000L;
    public static  int MAX_NUMBER = (int) (CALCULATE_NUMBER / 3);
    // 2000001
    static {
        long time = System.currentTimeMillis();
        long sum = 0;
        for(long i= 0 ;  i <CALCULATE_NUMBER ; i++){
            sum += i ;
        }
        System.out.println(sum);
        System.out.println("spend time:"+(System.currentTimeMillis() - time));
    }
    static class AddTask extends RecursiveTask<Long> {
        long start,end;
        AddTask(long start,long end){
            this.start = start;
            this.end = end;
        }
        @Override
        protected Long compute() {
            if( end - start < MAX_NUMBER){
                long sum = 0 ;
                for(long i = start ; i < end ; i ++){
                    sum += i ;
                }
                return sum;
            }else {
                long middle = (start + end ) / 2;
                AddTask sonTask1 = new AddTask(start, middle);
                AddTask sonTask2 = new AddTask(middle,end);
                sonTask1.fork();
                sonTask2.fork();
                return sonTask1.join() + sonTask2.join();
            }
        }
    }
    public static void main(String []args){
        ForkJoinPool fjp = new ForkJoinPool();
        AddTask task = new AddTask(0,CALCULATE_NUMBER);
        fjp.execute(task);
        long time = System.currentTimeMillis();
        long result = task.join();
        System.out.println("spend time:"+(System.currentTimeMillis() - time));
        System.out.println(result);
    }
}
