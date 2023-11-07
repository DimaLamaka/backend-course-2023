package ru.clevertec.concurrency.step6;

import lombok.SneakyThrows;

public class WriterExample {

    @SneakyThrows
    public static void main(String[] args) {
        //StringBuffer
        StringBuffer stringBuilder = new StringBuffer();
        Writer writer = new Writer(stringBuilder);

        WriterThread writerThread = new WriterThread("Thread#1", writer);
        WriterThread writerThread2 = new WriterThread("Thread#2", writer);

        writerThread.start();
        writerThread2.start();

        writerThread.join();
        writerThread2.join();
        System.out.println(stringBuilder);
    }

    static class WriterThread extends Thread {
        private final Writer writer;

        public WriterThread(String name, Writer writer) {
            super(name);
            this.writer = writer;
        }

        @Override
        public void run() {
            for (int i = 1; i <= 1000; i++) {
                writer.write(getName());
            }
        }
    }
}
