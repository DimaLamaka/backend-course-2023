package ru.clevertec.concurrency.step2;

import lombok.SneakyThrows;

public class ExceptionThreadExample {

    @SneakyThrows
    public static void main(String[] args) {
        System.out.println(Thread.currentThread() + " started");

        ExceptionThread exceptionThread = new ExceptionThread();
        Thread goodThread = new Thread(()-> System.out.println("All is good"));

        exceptionThread.start();
        goodThread.start();

        exceptionThread.join();
        goodThread.join();

        System.out.println(Thread.currentThread() + " ended");
    }

    static class ExceptionThread extends Thread {
        @Override
        public void run() {
            throw new RuntimeException();
        }
    }
}
