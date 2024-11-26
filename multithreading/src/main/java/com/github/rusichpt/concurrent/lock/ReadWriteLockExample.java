package com.github.rusichpt.concurrent.lock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockExample {
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private int value;

    public void read() {
        lock.readLock().lock(); // Захватываем блокировку для чтения
        try {
            System.out.println(Thread.currentThread().getName() + " прочитал значение: " + value);
        } finally {
            lock.readLock().unlock(); // Освобождаем блокировку
        }
    }

    public void write(int newValue) {
        lock.writeLock().lock(); // Захватываем блокировку для записи
        System.out.println("Захватываем блокировку для записи");
        try {
            System.out.println(Thread.currentThread().getName() + " записал значение: " + newValue);
            value = newValue;
        } finally {
            lock.writeLock().unlock(); // Освобождаем блокировку
            System.out.println("Освобождаем блокировку");
        }
    }

    public static void main(String[] args) {
        ReadWriteLockExample example = new ReadWriteLockExample();

        Runnable reader = () -> {
            while (true) {
                example.read();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        Runnable writer = () -> {
            for (int i = 0; i < 5; i++) {
                example.write(i);
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        Thread t1 = new Thread(reader, "Читатель 1");
        t1.setDaemon(true);
        Thread t2 = new Thread(reader, "Читатель 2");
        t2.setDaemon(true);
        Thread t3 = new Thread(reader, "Читатель 3");
        t3.setDaemon(true);
        Thread t4 = new Thread(writer, "Писатель");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
