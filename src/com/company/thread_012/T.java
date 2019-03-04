package com.company.thread_012;

import java.util.concurrent.TimeUnit;

/**
    volatile 关键字使得一个变量使得它在多个线程里可见

    volatile 不是使得线程访问的值会去不断地访问的.则会在主线程内存的值发生改变的时候，去读取其他
synchronized
    synchronized 既保证了可见性，又保证了原子性
    volatile 只是保证了可见性

 synchronized 比 volatile 效率要低得多
 */
public class T {
    boolean running = true;

    /**
     * volatile 可见性
     * java 的 内存模型
     * 为什么加了 volatile 之后， 数据就可以通讯了?
     * 按理说，另一个线程就结束了，实际上，并不是这个样子的
     * JMM
     * 每个线程在执行的过程中都有一块自己的内存，CPU的缓存区，就是线程存放自己变量的内存
     * 如果两个CPU在运行自己的内存的话,
     * 会有一个缓冲区， 原本有个主内存，
     * 两个线程会把自己的 主线程 的 内存读到 自己的缓存区里去了，
     * 然后它就开始运行这个线程，由于它非常忙,
     * 加了 volatile 以后，若修饰了volatile , 则当主线程中发生改变时，发出缓存过期通知
     *
     *
     * java里的通讯是通过 共享缓冲区
     * 线程之间的不可见，有可能会发生问题.
     * 当里面执行语句的时候，CPU 有点空闲的时候，就会到主内存中把值给刷过来了.
     *
     * volatile 比 加锁要提高很多很多
     * 保证线程间的无锁同步
     */
    void m(){
        System.out.println("m start");
        while (running){
            try {
                TimeUnit.MICROSECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("m end");
    }
    public static void main(String []args){
        T t = new T();
        new Thread(t::m,"t1").start();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.running = false;

    }
}
