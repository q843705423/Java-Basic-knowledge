package com.company.thread_016;

import java.util.concurrent.TimeUnit;

/**
 * 细粒度的锁比粗粒度的锁效率高
 */
public class T {
    synchronized void m1(){
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
