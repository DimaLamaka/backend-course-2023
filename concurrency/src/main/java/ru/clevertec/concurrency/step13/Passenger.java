package ru.clevertec.concurrency.step13;

import lombok.Getter;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

@Getter
public class Passenger extends Thread {
    private final Phaser phaser;
    private final int departure;
    private final int destination;

    public Passenger(Phaser phaser, int departure, int destination) {
        this.phaser = phaser;
        this.departure = departure;
        this.destination = destination;
        System.out.println(this + " ждёт на остановке № " + this.departure);
    }

    @Override
    public void run() {
        try {
            System.out.println(this + " сел в автобус.");

            while (phaser.getPhase() < destination) {
                phaser.arriveAndAwaitAdvance();
            }

            TimeUnit.MILLISECONDS.sleep(500);
            System.out.println(this + " покинул автобус.");
            phaser.arriveAndDeregister();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "Пассажир{" + departure + " -> " + destination + '}';
    }
}
