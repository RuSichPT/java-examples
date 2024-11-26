package com.github.rusichpt.concurrent.synchronizer;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample {
    public static void main(String[] args) throws InterruptedException {
        // Создаем CountDownLatch с 3 задачами
        CountDownLatch latch = new CountDownLatch(3);

        // Рабочая задача
        Runnable task = () -> {
            System.out.println(Thread.currentThread().getName() + " выполняет задачу...");
            try {
                Thread.sleep((long) (Math.random() * 2000)); // Симуляция работы
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println(Thread.currentThread().getName() + " завершил задачу.");
            latch.countDown(); // Уменьшаем счетчик
        };

        // Запускаем три рабочих потока
        for (int i = 0; i < 3; i++) {
            new Thread(task, "Рабочий " + (i + 1)).start();
        }

        // Главный поток ждет завершения всех задач
        System.out.println("Прораб ждет завершения всех задач...");
        latch.await(); // Блокируется, пока счетчик не станет 0
        System.out.println("Все задачи завершены. Продолжаем выполнение!");
    }
}
