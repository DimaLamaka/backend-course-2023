package ru.clevertec.concurrency.step2;

import lombok.SneakyThrows;

public class ThreadPriorityExample {

    @SneakyThrows
    public static void main(String[] args) {
        PriorityThread priorityThread1 = new PriorityThread("priorityThread1");
        PriorityThread priorityThread2 = new PriorityThread("priorityThread2");

        priorityThread2.setPriority(Thread.MAX_PRIORITY);
        priorityThread1.setPriority(Thread.MIN_PRIORITY);

        priorityThread1.start();
        priorityThread2.start();

        priorityThread1.join();
        priorityThread2.join();
    }

    static class PriorityThread extends Thread {
        public PriorityThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                System.out.println(i + " task, " + currentThread());
            }
        }
    }
}
