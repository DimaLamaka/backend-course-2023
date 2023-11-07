package ru.clevertec.concurrency.step2;

import java.util.concurrent.*;

public class StateExample {
    public static void main(String[] args) throws InterruptedException {
        Thread runnableThread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(i + ": " + Thread.currentThread());
                try {
                    TimeUnit.SECONDS.sleep(1);
                    if (i == 5) {
                        Thread.sleep(5000);
                    }

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "runnableThread");
        System.out.println(runnableThread.getName() + " state is: " + runnableThread.getState());

        runnableThread.start();
        System.out.println(runnableThread.getName() + " state is: " + runnableThread.getState());

        TimeUnit.SECONDS.sleep(8);
        System.out.println(runnableThread.getName() + " state is: " + runnableThread.getState());

        runnableThread.join();
        System.out.println(runnableThread.getName() + " state is: " + runnableThread.getState());

        System.out.println(runnableThread.getName() +" is alive:" + runnableThread.isAlive());
    }
}
