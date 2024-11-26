package com.github.rusichpt.concurrent.synchronizer;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample {
    public static void main(String[] args) {
        final int numberOfThreads = 3;

        // Действие, которое выполняется при достижении барьера
        Runnable barrierAction = () -> System.out.println("Все потоки достигли барьера. Продолжаем!");

        // Создаем CyclicBarrier
        CyclicBarrier barrier = new CyclicBarrier(numberOfThreads, barrierAction);

        Runnable task = () -> {
            try {
                for (int i = 0; i < 3; i++) { // Три цикла работы
                    System.out.println(Thread.currentThread().getName() + " выполняет задачу " + i);
                    Thread.sleep((long) (Math.random() * 1000)); // Симуляция работы
                    System.out.println(Thread.currentThread().getName() + " достиг барьера");
                    barrier.await(); // Ожидание на барьере
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        // Запускаем несколько потоков
        for (int i = 0; i < numberOfThreads; i++) {
            new Thread(task).start();
        }
    }
}
