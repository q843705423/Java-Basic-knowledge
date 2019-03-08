package com.company.thread_025;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 之前的是并发的，加锁的
 *
 * 阻塞式队列
 * 这是java生产消费模式的阻塞式实现
 * 这是链表实现的阻塞式实现
 *
 */
public class T05_LinkedBlockingQueue {
    // put方法，如果满了就会等待
    // take方法，如果空了就会等待
//    static BlockingQueue<String>queues = new LinkedBlockingQueue<>();
    //有界队列,如果add就会满了
    // 如果用offer，则会返回 false
    // offer 按照时间阻塞，如果1秒钟加不进去，就不往里面加了
    //而put方法满了会无限制的阻塞
    static BlockingQueue<String>queues = new ArrayBlockingQueue(10);
    public static void main(String []args){
        new Thread(()->{
            for(int i = 0 ; i< 100 ; i++){
                try {
                    queues.put(i+"");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(()->{
            try {
                queues.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }
}
