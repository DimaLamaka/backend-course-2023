package ru.clevertec.concurrency.step15;

import java.util.concurrent.BlockingQueue;

public class InThread extends Thread {
    private BlockingQueue<String> blockingQueue;

    public InThread(String name, BlockingQueue<String> blockingQueue) {
        super(name);
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                blockingQueue.put("Number " + i);
                System.out.println("Put number " + i);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
    }
}
