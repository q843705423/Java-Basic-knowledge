package com.company.thread_018;

/**
 * 不要以字符串常类作为锁定对象
 * 在下面这个例子中，m1和m2其实锁定的是同一个对象。
 * 这种的情况发生的非常诡异，有时候会让你摸不着头脑
 * 比如你用了一个类库，这个类库锁定了 "hello" 字符串
 * 比如你读不到源码，所以你在代码中也锁定了hello，这时候死锁就容易发生了
 * 因为你的程序不经意间使用了同一把锁
 *
 */
public class T {
    String s1 = "hello";
    String s2 = "hello";
    void m1(){
       synchronized (s1){
           System.out.println("hello m1");
       }
    }
    void m2(){
        synchronized(s2){
            while (true){
                System.out.println("hello m2");
            }
        }
    }

    public static void main(String []args){
        T t = new T();
        new Thread(t::m1).start();
        new Thread(t::m2).start();
    }
}
