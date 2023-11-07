package ru.clevertec.concurrency.step4;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceExample {

    @Data
    @Accessors(chain = true)
    @AllArgsConstructor
    static class Person {
        private String name;
    }

    public static void main(String[] args) {
        AtomicReference<String> stringRef = new AtomicReference<>("Init value ");

        Thread thread1 = new Thread(() -> {
            boolean success = false;
            while (!success){
                String prevValue = stringRef.get();
                String nextValue = stringRef.get() + "thread1 value ";
                success = stringRef.compareAndSet(prevValue,nextValue);
            }
        });

        Thread thread2 = new Thread(() -> {
            boolean success = false;
            while (!success){
                String prevValue = stringRef.get();
                String nextValue = stringRef.get() + "thread2 value ";
                success = stringRef.compareAndSet(prevValue,nextValue);
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("New value = " + stringRef.get());
    }
}
