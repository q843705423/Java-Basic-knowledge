package com.company.thread_026;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

//偷工作的线程池
//每个线程都维护自己的队列
//然后它会去把别人的任务偷过来
public class T11_WorkStealingPool {
    public static void main(String []args) throws IOException {
        ExecutorService work = Executors.newWorkStealingPool();
        //精灵线程
        work.execute(new R(1000));
        work.execute(new R(2000));
        work.execute(new R(2000));
        work.execute(new R(2000));
        work.execute(new R(2000));
        //只要虚拟机不退出，那么它就不退出
        System.in.read();
    }
    static class R implements Runnable{
        int delay;
        R(int time){

           delay  = time;
        }
        @Override
        public void run() {
            try {
                TimeUnit.MILLISECONDS.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
        }
    }
}
