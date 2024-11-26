package com.github.rusichpt.problem.monitorblocking;

import java.util.concurrent.locks.ReentrantLock;

public class LockInterruptiblyExample {

    private final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        LockInterruptiblyExample example = new LockInterruptiblyExample();

        // Поток 1 захватывает блокировку и удерживает её
        Thread thread1 = new Thread(() -> {
            example.lock.lock();
            try {
                System.out.println("Thread 1: Lock acquired, working...");
                Thread.sleep(10_000); // Удерживает блокировку 10 секунд
            } catch (InterruptedException e) {
                System.out.println("Thread 1: Interrupted during work");
            } finally {
                example.lock.unlock();
                System.out.println("Thread 1: Lock released");
            }
        });

        // Поток 2 пытается захватить блокировку с возможностью прерывания
        Thread thread2 = new Thread(() -> {
            try {
                System.out.println("Thread 2: Trying to acquire the lock...");
                example.lock.lockInterruptibly(); // Ожидает блокировки с учётом прерывания
                try {
                    System.out.println("Thread 2: Lock acquired, working...");
                } finally {
                    example.lock.unlock();
                    System.out.println("Thread 2: Lock released");
                }
            } catch (InterruptedException e) {
                System.out.println("Thread 2: Interrupted while waiting for the lock");
            }
        });

        thread1.start();
        Thread.sleep(100); // Даем thread1 время захватить блокировку

        thread2.start();
        Thread.sleep(100); // Даем thread2 время попробовать захватить блокировку

        System.out.println("Main: Interrupting Thread 2");
        thread2.interrupt(); // Прерываем thread2

        thread1.join();
        thread2.join();

        System.out.println("Main: Both threads are finished.");
    }
}
