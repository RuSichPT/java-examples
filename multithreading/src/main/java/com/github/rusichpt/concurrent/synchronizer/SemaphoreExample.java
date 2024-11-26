package com.github.rusichpt.concurrent.synchronizer;

import java.util.concurrent.Semaphore;

public class SemaphoreExample {
    private static final int MAX_THREADS = 3;
    private static final Semaphore semaphore = new Semaphore(MAX_THREADS, true);

    public static void main(String[] args) {
        Runnable task = () -> {
            try {
                System.out.println(Thread.currentThread().getName() + " пытается получить доступ.");
                semaphore.acquire(); // Запрашиваем разрешение
                System.out.println(Thread.currentThread().getName() + " получил доступ.");

                // Симуляция работы
                Thread.sleep(2000);

                System.out.println(Thread.currentThread().getName() + " освобождает доступ.");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                semaphore.release(); // Освобождаем разрешение
            }
        };

        // Запускаем 10 потоков
        for (int i = 0; i < 10; i++) {
            new Thread(task).start();
        }
    }
}
