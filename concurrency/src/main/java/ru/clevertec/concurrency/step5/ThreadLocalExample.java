package ru.clevertec.concurrency.step5;

public class ThreadLocalExample {
    private static final ThreadLocal<Integer> COUNTER = ThreadLocal.withInitial(() -> -1);

    public static void setCounter(int value) {
        COUNTER.set(value);
    }

    public static int getCounter() {
        return COUNTER.get();
    }

    public static void main(String[] args) {
        setCounter(42);
        System.out.println(Thread.currentThread() + " " + getCounter());

        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread() + " " + getCounter());
            setCounter(33);
            System.out.println(Thread.currentThread() + " " + getCounter());
        },"worker1");

        Thread thread2 = new Thread(() -> System.out.println(Thread.currentThread() + " " + getCounter()),"worker2");
        WorkerThread workerThread = new WorkerThread("worker3");

        thread.start();
        thread2.start();
        workerThread.start();
    }

    static class WorkerThread extends Thread {
        public WorkerThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread() + " " + ThreadLocalExample.getCounter());
            ThreadLocalExample.setCounter(666);
            System.out.println(Thread.currentThread() + " " + ThreadLocalExample.getCounter());
        }
    }
}
