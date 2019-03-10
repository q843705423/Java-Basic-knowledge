package com.company.thread_021;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 上面那个面试题除了可以用wait和notify实现以外，还可以用lock和condition来实现
 *  而且这种方式能够精确唤醒那些进行线程
 *  lock await
 *  object wait signed
 */
public class MyContainer2<T> {
    private LinkedList<T> linkedList = new LinkedList<>();
    private int MAX = 10;
    private Lock lock  = new ReentrantLock();
    private Condition producerCondition = lock.newCondition();
    private Condition cunsumerCondition = lock.newCondition();
    public void put(T t){
        lock.lock();
        try {
            while (linkedList.size() == MAX){
                producerCondition.await();
            }
            linkedList.add(t);
            cunsumerCondition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public T get(){
        T t = null;
        lock.lock();
        try {
            while (linkedList.size() ==  0 ) {
                cunsumerCondition.await();
            }
            t = linkedList.removeFirst();
            producerCondition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return t;
    }
    public static void main(String []args){
        MyContainer2<String> c = new MyContainer2<>(); 
        for(int i = 0 ; i < 10 ; i ++){
            new Thread(()->{
                for(int j = 0 ; j < 3 ; j ++){
                    String value = c.get();
                    System.out.println(value + " is consumered by " + Thread.currentThread().getName());
                }
            },"consumer"+ (i + 1)).start();
        }
        for (int i = 0 ; i < 5 ; i ++ ){
            new Thread(()->{
                for( int j = 0 ; j < 6 ; j ++){
                    c.put(Thread.currentThread().getName()+ " " +  j);
                }
            },"producer" + (i + 1)).start();
        }
    }
}
