package com.company.thread_001;

public class T {
    private int count = 10;
    private Object o = new Object();
    //注意，以上的代码做了两件事情
    //1. 在堆内存中创建了一个对象 new Object()
    //2. 在栈内存中创建了对象的引用，并且指向了堆内存中创建的那个对象
    // 而 锁的对象就是存放在堆内存中，此时，如果o的引用不再指向这个object，那么锁的对象就变了.
    // 如果一个线程先执行到 #1处
    // 那么它已经拿到了 o的锁，这个时候，如果第二个线程想要申请o的锁，则会被阻塞
    // synchronized 造成的锁，即为互斥锁
    public void m(){
        synchronized (o){ //任何线程要执行下面的代码，必须要拿到o的锁
            //不加锁，就不让你执行这段代码
            count --;
            //#1
            System.out.println(Thread.currentThread().getName()+",count="+count);
        }
    }
}
