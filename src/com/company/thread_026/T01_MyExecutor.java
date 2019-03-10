package com.company.thread_026;

import java.util.concurrent.Executor;

//执行器
//执行接口，在顶部执行任务
//在整个java线程池中已经写了一堆的实现
//我们一般不需要使用这个接口
public class T01_MyExecutor implements Executor {
    public static void main(String []args){
        new T01_MyExecutor().execute(()-> System.out.println("hello world"));
        
    }
    @Override 
    public void execute(Runnable runnable){
        runnable.run();
    }
}
