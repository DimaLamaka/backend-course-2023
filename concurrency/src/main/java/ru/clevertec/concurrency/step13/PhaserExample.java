package ru.clevertec.concurrency.step13;

import java.util.ArrayList;
import java.util.concurrent.Phaser;

public class PhaserExample {
    public static void main(String[] args) {
        Phaser phaser = new Phaser(1);
        ArrayList<Passenger> passengers = new ArrayList<>();

        for (int i = 1; i < 5; i++) {           //Сгенерируем пассажиров на остановках
            if ((int) (Math.random() * 2) > 0) {
                passengers.add(new Passenger(phaser, i, i + 1));
            }//Этот пассажир выходит на следующей

            if ((int) (Math.random() * 2) > 0) {
                passengers.add(new Passenger(phaser, i, 5));
            }//Этот пассажир выходит на конечной
        }

        for (int i = 0; i < 7; i++) {
            switch (i) {
                case 0:
                    System.out.println("Автобус выехал из парка.");
                    phaser.arrive();//В фазе 0 всего 1 участник - автобус
                    break;
                case 6:
                    System.out.println("Автобус уехал в парк.");
                    phaser.arriveAndDeregister();//Снимаем главный поток, ломаем барьер
                    break;
                default:
                    int currentBusStop = phaser.getPhase();
                    System.out.println("Остановка № " + currentBusStop);

                    for (Passenger p : passengers)          //Проверяем, есть ли пассажиры на остановке
                        if (p.getDeparture() == currentBusStop) {
                            phaser.register();//Регистрируем поток, который будет участвовать в фазах
                            p.start();        // и запускаем
                        }

                    phaser.arriveAndAwaitAdvance();//Сообщаем о своей готовности
            }
        }
    }
}
