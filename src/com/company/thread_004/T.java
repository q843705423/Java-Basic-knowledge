package com.company.thread_004;

public class T {
    private static int count = 10;
    public static synchronized void m(){//这里等同于 synchronized(T.class)
        System.out.println(Thread.currentThread().getName()+",count:"+count);
    }

    public static void mm(){
        //这里是不能写this的，如果你这里进行锁定的话，就相当于锁住class对象
        synchronized (T.class){
            count --;
        }
    }
}
