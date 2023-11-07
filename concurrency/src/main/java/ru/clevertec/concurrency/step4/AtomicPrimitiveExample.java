package ru.clevertec.concurrency.step4;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicPrimitiveExample {
    private static AtomicInteger atomicInteger = new AtomicInteger();

    @SneakyThrows
    public static void main(String[] args) {
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 1_000_000; j++) {
                    atomicInteger.getAndIncrement();
                                    }
            });
            threads.add(thread);
            thread.start();
        }

        for (Thread thread: threads) {
            thread.join();
        }
        System.out.println("counter = " + atomicInteger.get());
    }
}
