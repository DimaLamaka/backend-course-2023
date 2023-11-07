package ru.clevertec.concurrency.step2;

public class InterruptExample {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println(Thread.currentThread() + " : Working...");
            }
            System.out.println(Thread.currentThread() + "Interrupted!");
        },"thread1");

        Thread thread2 = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    System.out.println(Thread.currentThread() + " : Working...");
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread() + "Interrupted with exception!");
                    Thread.currentThread().interrupt();
                }
            }
        },"thread2");

        thread.start();
        thread2.start();

        try {
            Thread.sleep(2000);
            thread.interrupt();
            thread2.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
