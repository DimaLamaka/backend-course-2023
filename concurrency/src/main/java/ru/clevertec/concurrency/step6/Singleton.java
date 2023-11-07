package ru.clevertec.concurrency.step6;

import lombok.SneakyThrows;

public class Singleton {
    private String foo;
    private int bar;
    private static Singleton instance;

    private Singleton() {
        foo = "foo";
        bar = 1;
    }

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    @SneakyThrows
    public static void main(String[] args) {
        Thread thread = new Thread(() -> System.out.println(Singleton.getInstance()));
        Thread thread1 = new Thread(() -> System.out.println(Singleton.getInstance()));
        Thread thread2 = new Thread(() -> System.out.println(Singleton.getInstance()));
        Thread thread3 = new Thread(() -> System.out.println(Singleton.getInstance()));
        Thread thread4 = new Thread(() -> System.out.println(Singleton.getInstance()));
        Thread thread5 = new Thread(() -> System.out.println(Singleton.getInstance()));
        Thread thread6 = new Thread(() -> System.out.println(Singleton.getInstance()));
        Thread thread7 = new Thread(() -> System.out.println(Singleton.getInstance()));

//        Thread thread = new Thread(() -> System.out.println(Singleton.getInstance().foo));
//        Thread thread1 = new Thread(() -> System.out.println(Singleton.getInstance().bar));
//        Thread thread2 = new Thread(() -> System.out.println(Singleton.getInstance().foo));
//        Thread thread3 = new Thread(() -> System.out.println(Singleton.getInstance().bar));
//        Thread thread4 = new Thread(() -> System.out.println(Singleton.getInstance().foo));
//        Thread thread5 = new Thread(() -> System.out.println(Singleton.getInstance().bar));
//        Thread thread6 = new Thread(() -> System.out.println(Singleton.getInstance().foo));
//        Thread thread7 = new Thread(() -> System.out.println(Singleton.getInstance().bar));

        thread.start();
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread6.start();
        thread7.start();

        thread.join();
        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();
        thread5.join();
        thread6.join();
        thread7.join();
    }
}
