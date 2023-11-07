package ru.clevertec.concurrency.step14;

import java.util.concurrent.Exchanger;

public class ExchangerExample {
    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();

        Thread thread1 = new Thread(() -> {
            try {
                String message = "Hello from Thread 1";
                System.out.println("Thread 1 sends: " + message);
                String receivedMessage = exchanger.exchange(message);
                System.out.println("Thread 1 received: " + receivedMessage);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                String message = "Hello from Thread 2";
                System.out.println("Thread 2 sends: " + message);
                String receivedMessage = exchanger.exchange(message);
                System.out.println("Thread 2 received: " + receivedMessage);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread1.start();
        thread2.start();
    }
}
