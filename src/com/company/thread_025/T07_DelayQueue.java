package com.company.thread_025;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 很好玩的队列
 * 等待时间最长的先往外面拿走
 * 只有等一段时间之后，才能往后拿
 * DelayQueue
 * 可以用来做定时任务的
 */
public class T07_DelayQueue {
    static BlockingQueue<String>queue = new DelayQueue();
    public static void main(String []args){
        queue.add("1");
    }
}
class Hello implements Delayed{

    @Override
    public long getDelay(TimeUnit unit) {
        return 0;
    }

    @Override
    public int compareTo(Delayed o) {
        return 0;
    }
}
