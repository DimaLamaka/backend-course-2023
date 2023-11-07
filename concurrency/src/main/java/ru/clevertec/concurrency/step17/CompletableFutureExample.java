package ru.clevertec.concurrency.step17;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class CompletableFutureExample {
    interface Worker {
        String doWork();
    }

    private static final List<Worker> WORKERS = new ArrayList<>();

    static {
        WORKERS.add(() -> {
            System.out.println(Thread.currentThread() + "start work1");
            String value = Thread.currentThread() + "do work1";
            System.out.println(value);
            sleep(4);
            System.out.println(Thread.currentThread() + "end work1");
            return value;
        });
        WORKERS.add(() -> {
            System.out.println(Thread.currentThread() + "start work2");
            String value = Thread.currentThread() + "do work2";
            System.out.println(value);
            sleep(4);
            System.out.println(Thread.currentThread() + "end work2");
            return value;
        });
        WORKERS.add(() -> {
            System.out.println(Thread.currentThread() + "start work3");
            String value = Thread.currentThread() + "do work3";
            System.out.println(value);
            sleep(4);
            System.out.println(Thread.currentThread() + "end work3");
            return value;
        });
        WORKERS.add(() -> {
            System.out.println(Thread.currentThread() + "start work4");
            String value = Thread.currentThread() + "do work4";
            System.out.println(value);
            sleep(8);
            System.out.println(Thread.currentThread() + "end work4");
            return value;
        });
    }

    public static void main(String[] args) {
        List<String> results = WORKERS.stream()
                .map(worker -> CompletableFuture.supplyAsync(worker::doWork))
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
        System.out.println("RESULT 1 IS: " + results);
        System.out.println("------------------------------------");



        List<CompletableFuture<String>> tasks = WORKERS.stream()
                .map(worker -> CompletableFuture.supplyAsync(worker::doWork))
                .collect(Collectors.toList());

        String result = CompletableFuture.allOf(tasks.toArray(new CompletableFuture[0]))
                .thenApply(all -> tasks.stream()
                        .map(CompletableFuture::join)
                        .collect(Collectors.toList()))
                .thenApply(List::toString)
                .completeOnTimeout("default value", 5, TimeUnit.SECONDS)
                .exceptionally(exc -> {
                    throw new RuntimeException(exc);
                })
//                .orTimeout(5,TimeUnit.SECONDS)
                .join();

        System.out.println("RESULT 2 IS: " + result);
    }

    @SneakyThrows
    private static void sleep(long seconds) {
        TimeUnit.SECONDS.sleep(seconds);
    }
}
