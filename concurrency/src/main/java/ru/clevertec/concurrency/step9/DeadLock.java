package ru.clevertec.concurrency.step9;

import java.util.concurrent.TimeUnit;

public class DeadLock {
    public static void main(String[] args) {
        Object lock1 = new Object();
        Object lock2 = new Object();

        Thread thread1 = new Thread(
                () -> {
                    System.out.println(Thread.currentThread() + ": Start handle");
                    synchronized (lock1) {
                        try {
                            TimeUnit.MILLISECONDS.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        synchronized (lock2) {

                        }
                    }
                    System.out.println(Thread.currentThread() + ": End handle");
                }
                , "Thread1");

        Thread thread2 = new Thread(
                () -> {
                    System.out.println(Thread.currentThread() + ": Start handle");
                    synchronized (lock2) {
                        synchronized (lock1) {

                        }
                    }
                    System.out.println(Thread.currentThread() + ": End handle");
                }
                , "Thread2");

        thread1.start();
        thread2.start();
    }
}
