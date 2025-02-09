package com.github.rusichpt.problem.monitorblocking;

public class MonitorBlockingExample {

    private final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        MonitorBlockingExample example = new MonitorBlockingExample();

        // Поток 1 захватывает монитор и удерживает его
        Thread thread1 = new Thread(() -> {
            synchronized (example.lock) {
                try {
                    System.out.println("Thread 1: Holding the lock...");
                    Thread.sleep(10_000); // Удерживает монитор 10 секунд
                } catch (InterruptedException e) {
                    System.out.println("Thread 1: Interrupted during sleep");
                }
            }
        });

        // Поток 2 пытается захватить монитор
        Thread thread2 = new Thread(() -> {
            synchronized (example.lock) {
                System.out.println("Thread 2: Acquired the lock!");
            }
        });

        thread1.start();
        Thread.sleep(100); // Даем thread1 время захватить монитор

        thread2.start();
        Thread.sleep(100); // Даем thread2 время попробовать захватить монитор

        System.out.println("Main: Interrupting Thread 2");
        thread2.interrupt(); // Прерываем thread2 (не можем прервать состояние BLOCKING)

        thread1.join();
        thread2.join();

        System.out.println("Main: Both threads are finished.");
    }
}
