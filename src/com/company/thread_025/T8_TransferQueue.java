package com.company.thread_025;

import java.util.concurrent.LinkedTransferQueue;

public class T8_TransferQueue {
    public static void main(String []args){
        LinkedTransferQueue<String>strings = new LinkedTransferQueue<>();
        new Thread(()->{
            try {
                System.out.println(strings.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        try {
            /**
             * 提供了transfer 方法，
             * 这里有一个队列
             * 消费者先启动,
             * 生产者不往里面扔下去
             *
             * 找不到消费者就会阻塞
             */
            strings.transfer("aaa");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
