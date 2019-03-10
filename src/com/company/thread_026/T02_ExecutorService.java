package com.company.thread_026;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

/**
 * ExecutorService
 * 执行器的服务，等待扔任务
 * 除了可以执行 Runnable ，还可以执行 callback 接口
 * 接下来讲的类基本就是 它的实现类
 */

/**
 * Runnable 无返回值
 * Callable 可以有返回值
 * 当你需要线程运行完了有返回值的时候，那么就使用Callable
 * 不然的话，你就可以使用Runnable
 * 最主要的区别就是一个有返回值，另外一个没有
 */
public class T02_ExecutorService  implements ExecutorService {
    @Override
    public void execute(Runnable command) {
        
    }
    public static void main(String []args){

    }

    @Override
    public void shutdown() {

    }

    @Override
    public List<Runnable> shutdownNow() {
        return null;
    }

    @Override
    public boolean isShutdown() {
        return false;
    }

    @Override
    public boolean isTerminated() {
        return false;
    }

    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        return null;
    }

    @Override
    public <T> Future<T> submit(Runnable task, T result) {
        return null;
    }

    @Override
    public Future<?> submit(Runnable task) {
        return null;
    }

    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException {
        return null;
    }

    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException {
        return null;
    }

    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException {
        return null;
    }

    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return null;
    }
}
