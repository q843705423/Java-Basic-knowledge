package com.company.thread_025;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class T09_SynchronusQueue {
    /**
     * 如果消费者，队列是会有一定容量的
     * synchronusQueue队列的容量为0
     * 消费者必须马上消费掉
     *
     * put在这里阻塞等待消费者消费,其实里面用的就是transferQueue
     *
     */
    public static void main(String []args){
        BlockingQueue<String> q = new SynchronousQueue<>();
        try {
            q.put("123");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
