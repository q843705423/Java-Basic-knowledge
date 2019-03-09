package com.company.thread_026;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class T14_ParallelSteam {
    public static void main(String []args){
        List<Integer>nums = new ArrayList<>();
        Random random = new Random();
        for(int i= 0 ; i < 20000; i ++){
            nums.add((int )1e6+ random.nextInt((int) 1e6));
        }
        long start = System.currentTimeMillis();
        nums.forEach(T14_ParallelSteam::isPrime);
        System.out.println(System.currentTimeMillis() - start);

        start = System.currentTimeMillis();
        nums.parallelStream().forEach(T14_ParallelSteam::isPrime);
        System.out.println(System.currentTimeMillis() - start);

    }
    public static boolean isPrime(int num){
        if(num == 1 |  num == 2){
            return false;
        }
        for(int i = 2; i < num ; i++){
            if( num % i == 0){
                return false;
            }
        }
        return true;
    }

}
