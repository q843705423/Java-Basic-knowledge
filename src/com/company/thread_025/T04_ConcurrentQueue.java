package com.company.thread_025;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Queue
 * 在并发容器里是非常重要，而且是用的最多的
 *
 * 双端队列 ConcurrentLinkedDeque

 */
public class T04_ConcurrentQueue {
    public static void main(String []args){
        Queue<String>queue = new ConcurrentLinkedQueue<>();
        //add 如果有容量限制会抛出异常
        //offer不会抛出异常，但是有返回值
        for(int i = 0 ; i < 10; i ++){
            queue.offer("a" + 1);
        }
        // 1->2->3->4->5-6
        //即为从顶部拿出一个,并且删除
        queue.poll();

        //拿出来但是不删除
        queue.peek();
    }

}
