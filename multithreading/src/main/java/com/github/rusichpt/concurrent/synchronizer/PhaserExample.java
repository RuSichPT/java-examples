package com.github.rusichpt.concurrent.synchronizer;

import java.util.concurrent.Phaser;

public class PhaserExample {
    public static void main(String[] args) {
        Phaser phaser = new Phaser(1); // Главный поток регистрируется сам

        int numTasks = 3;

        for (int i = 0; i < numTasks; i++) {
            final int taskID = i;
            phaser.register(); // Регистрируем новый поток
            new Thread(() -> {
                try {
                    System.out.println("Task " + taskID + " started phase 1");
                    Thread.sleep(1000); // Симулируем работу
                    phaser.arriveAndAwaitAdvance(); // Завершаем фазу 1 и ждем

                    System.out.println("Task " + taskID + " started phase 2");
                    Thread.sleep(1000); // Симулируем работу
                    phaser.arriveAndAwaitAdvance(); // Завершаем фазу 2 и ждем

                    System.out.println("Task " + taskID + " completed all phases");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }).start();
        }

        phaser.arriveAndAwaitAdvance(); // Главный поток ждет завершения первой фазы
        System.out.println("Phase 1 completed");

        phaser.arriveAndAwaitAdvance(); // Главный поток ждет завершения второй фазы
        System.out.println("Phase 2 completed");

        phaser.arriveAndDeregister(); // Главный поток завершает свои фазы
    }
}
