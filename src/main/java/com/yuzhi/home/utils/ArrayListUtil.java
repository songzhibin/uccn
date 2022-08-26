package com.yuzhi.home.utils;


import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ArrayListUtil {
    private static ReentrantReadWriteLock reentrantReadWriteLock;

    public static void main(String[] args) throws InterruptedException {
        List<String> list = new ArrayList<>();

        int oldCapacity = 10;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        System.out.println(newCapacity);
        System.out.println(oldCapacity >> 3);

        Hashtable<String, Object> hashtable = new Hashtable<>();
        CountDownLatch countDownLatch = new CountDownLatch(3);
        countDownLatch.countDown();

       new ReentrantLock(true);

        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        reentrantReadWriteLock.readLock();
        reentrantReadWriteLock.writeLock();
        reentrantReadWriteLock.wait(100L);
    }
//1010  2^3 + 2^1 = 10
//101 2^2+2^0 = 5
//    10 2^1
//    1 2^0
}
