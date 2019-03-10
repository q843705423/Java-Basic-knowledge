package com.company.thread_021;

import com.company.thread_019.MyContainer5;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * 面试题
 * 些一个固定容量的容器，
 * 拥有put和get方法以及getCount方法
 * 支持2个生产者线程，10个消费者线程的阻塞调用
 * @param <T>
 *
 */

/**
 * 同步容器，如果满了，那么调用put的进程就得等着
 * 如果空了，那么调用 get的线程就得等着
 * @param <T>
 */
public class MyContainer1<T> {
    final public LinkedList<T> list= new LinkedList<T>();
    final private int MAX = 10;
    private int count = 0 ;
    public synchronized void put(T t){
        //为什么要用while?
        //据说 wait有99%与 while结合使用
        while(list.size()==MAX){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        list.add(t);
        ++ count;
        //通知消费者进行消费
        this.notifyAll();
        //为什么是notifyAll而不是notify,因为如果只叫醒一个
    }
    public synchronized T get(){
        T t = null;
        while (list.size() == 0 ){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        count -- ;
        t = list.removeFirst();
        this.notifyAll();
        //通知生产者生产
        return t;
    }

    public int getCount() {
        return count;
    }
    public static void main(String []args){
        MyContainer1<String> c = new MyContainer1<>();
        for(int i = 0 ; i < 10 ; i ++){
            new Thread(()->{
               for(int j = 0 ; j < 5 ; j ++){
                   System.out.println(c.get());
               }
            },"consumer" + i ).start();
        }
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for(int i = 0 ; i < 2 ; i ++){
            new Thread(()->{
                for(int j = 0 ; j < 25 ; j ++){
                    c.put(Thread.currentThread().getName()+" "+ j );
                }
            },"producer" +  i ).start();
        }


    }
}
