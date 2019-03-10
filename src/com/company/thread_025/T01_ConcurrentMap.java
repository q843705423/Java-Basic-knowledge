package com.company.thread_025;

import java.util.*;
import java.util.concurrent.CountDownLatch;

public class T01_ConcurrentMap {
    public static void main(String []args){
        /**
         * 据说
         * Hashtable是锁定整个对象的
         *
         * 而 ConcurrentHashMap 将 其分为16端，那么相当于将大锁分成了小锁
         * 而TreeMap是排好顺序的，插入效率低，高并发情况下，并且排序，效率更加低
         *
         * ConcurrentSkipListMap  高并发并且排序
         * 跳表
         * 插入速度慢
         * 查找速度快
         *
         * map/set 选择
         * 不加锁 Hashmap,treemap,linkedhashmap
         *
         * 加锁
         * 并发性不高的情况
         * hashTable /
         * synchronizedMap / synchronizedList 可以输入非加锁容器，输出加锁的容器
         *
         * 并发性比较高
         * concurrentHashMap
         * concurrentHashSet
         *
         * 并发性比较高且需要进行排序的
         * concurrentSkipListMap
         * concurrentSkipListSet
         *
         */
//        Map<String,String>map = new ConcurrentHashMap<>();
        Map<String,String> map = new Hashtable<>();
        Random r = new Random();
        int THREAD_NUMBER = (int)1e3;
        Thread[]threads = new Thread[THREAD_NUMBER];
        CountDownLatch latch = new CountDownLatch(threads.length);
        long start = System.currentTimeMillis();
        int MAX_NUMBER = (int) 1e5;
        int INSERT_NUMBER = (int) 1e5;
        for(int i = 0 ; i < threads.length ; i ++){
            threads[i] = new Thread(()->{
                for(int j = 0 ; j < INSERT_NUMBER ;j++){
                    map.put("a"+ r.nextInt(MAX_NUMBER),"a"+r.nextInt(MAX_NUMBER));
                }
                latch.countDown();
            });
        }
        Arrays.asList(threads).forEach(t->t.start());
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

}
