package ru.clevertec.concurrency.step15;

import java.util.concurrent.BlockingQueue;

public class OutThread extends Thread {
    private BlockingQueue<String> blockingQueue;

    public OutThread(String name, BlockingQueue<String> blockingQueue) {
        super(name);
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                String take = blockingQueue.take();
                System.out.println("take number " + take);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
    }
}
