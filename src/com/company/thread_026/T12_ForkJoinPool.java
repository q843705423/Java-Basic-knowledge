package com.company.thread_026;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

//Task -> sonTask1,sonTask2,sonTask3.....  -> 可以分到比较小，最后再合并
public class T12_ForkJoinPool {
    //bjmashibing
    static int[]nums = new int[(int) 1e6];
    static final int MAX_NUM = 50000;
    static Random r = new Random();
    static {
        for (int i = 0; i < nums.length; i++) {
            nums[i] = r.nextInt(100);
        }
        int ans = Arrays.stream(nums).sum();
    }
    static class AddTask extends RecursiveAction{//RecurrisveTask
        int start,end;
        AddTask(int s,int e){
            start = s;
            end = e;
        }

        @Override
        protected void compute() {
            if(end - start < MAX_NUM){
                int sum = 0;
                for(int i = start ; i < end ; i++){
                    sum += nums[i];
                }
                System.out.println(sum);
            }else {
                int middle = start +(end -start)/2;
                AddTask subTask1 = new AddTask(start,middle);
                AddTask subTask2 = new AddTask(middle,end);
                subTask1.fork();
                subTask2.fork();
            }
        }
    }
    public static void main(String []args){
        ForkJoinPool fjp = new ForkJoinPool();
        AddTask addTask = new AddTask(1,1000000);
        fjp.execute(addTask);
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
