package com.company.thread_008;

import java.util.concurrent.TimeUnit;

/**
 * 可以只给写方法加锁，不给读的方法加锁吗?
 * 不可以，因为这样容易发生脏读,
 *  比如以下代码，
 *  比如执行一个耗时的修改账户余额操作，
 *  但是如果该耗时操作没有执行完，
 *  这时候，如果一个没有加锁的读方法是可以直接读取之前的数据的
 *  这样就发生了脏读
 */

/**
 * 如何解决？
 * 只要在读的时候加一个 synchronized 就可以避免脏读。
 * 当然有些业务逻辑也允许脏读,这样对性能有很大的提升
 */
public class Account {
    private String account;
    private double balance;

    public String getAccount() {
        return account;
    }
    public synchronized  void set(String account,double balance) {
        this.account = account;
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.balance = balance;
    }
    public double getBalance() {
        return balance;
    }
    public static void main(String []args){
        Account account = new Account();
        new Thread(()->account.set("zhangsan",100.0)).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(account.getBalance());
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(account.getBalance());
    }
}
