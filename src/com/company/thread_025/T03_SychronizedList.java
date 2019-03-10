package com.company.thread_025;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 使用 sychronizedXXX 可以把一个非线程安全的容器变成一个线程安全的容器
 */
public class T03_SychronizedList {
    public static void main(String []args){
        List<String> list = new ArrayList<>();
        list = Collections.synchronizedList(list);
        System.out.println(list);
    }
}
