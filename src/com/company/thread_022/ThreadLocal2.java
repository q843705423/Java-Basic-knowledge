package com.company.thread_022;

import java.util.concurrent.TimeUnit;

public class ThreadLocal2 {

    /**
     * 有些时候，我不想让自己的改变，让另外的一个线程知道
     * 这时候，我们就应该使用ThreadLocal，即线程局部变量
     * @param args
     */
    static ThreadLocal<Student> studentThreadLocal = new ThreadLocal<>();
    public static void main(String []args){
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(studentThreadLocal.get());
        }).start();
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Student student = new Student();
            student.name = "Tom";
            studentThreadLocal.set(student);
        }).start();
    }
}
class Student{
   String name;
}
