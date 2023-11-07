package ru.clevertec.concurrency.step8.example3;


import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockExample {

    @SneakyThrows
    public static void main(String[] args) {

        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        ReadWriteLockResource readWriteLockResource = new ReadWriteLockResource(new StringBuilder());

        WriterThread writerThread1 = new WriterThread("Writer #1", readWriteLockResource, readWriteLock);
        WriterThread writerThread2 = new WriterThread("Writer #2", readWriteLockResource, readWriteLock);

        ReadThread readerThread1 = new ReadThread("Reader #1", readWriteLockResource, readWriteLock);
        ReadThread readerThread2 = new ReadThread("Reader #2", readWriteLockResource, readWriteLock);
        ReadThread readerThread3 = new ReadThread("Reader #3", readWriteLockResource, readWriteLock);
        ReadThread readerThread4 = new ReadThread("Reader #4", readWriteLockResource, readWriteLock);
        ReadThread readerThread5 = new ReadThread("Reader #5", readWriteLockResource, readWriteLock);

        writerThread1.start();
        TimeUnit.SECONDS.sleep(1);

        readerThread1.start();
        readerThread2.start();
        readerThread3.start();

        writerThread2.start();

        readerThread4.start();
        readerThread5.start();
    }
}
