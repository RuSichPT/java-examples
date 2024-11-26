package com.github.rusichpt.problem;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerExample {
    // Буфер (очередь)
    private static final Queue<Integer> buffer = new LinkedList<>();
    private static final int BUFFER_SIZE = 5;

    // Замок и условия
    private static final Lock lock = new ReentrantLock();
    private static final Condition notFull = lock.newCondition();
    private static final Condition notEmpty = lock.newCondition();

    public static void main(String[] args) {
        // Поток производителя
        Thread producer = new Thread(() -> {
            try {
                while (true) {
                    lock.lock();
                    try {
                        while (buffer.size() == BUFFER_SIZE) {
                            System.out.println("Буфер полный. Производитель ожидает...");
                            notFull.await();  // Ожидание, если буфер полный
                        }
                        int item = (int) (Math.random() * 100);
                        buffer.offer(item);  // Производитель добавляет элемент в буфер
                        System.out.println("Производитель добавил: " + item);
                        notEmpty.signal();  // Уведомляем потребителя, что буфер не пуст
                    } finally {
                        lock.unlock();
                    }

                    Thread.sleep(1000);  // Эмуляция времени работы
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Поток потребителя
        Thread consumer = new Thread(() -> {
            try {
                while (true) {
                    lock.lock();
                    try {
                        while (buffer.isEmpty()) {
                            System.out.println("Буфер пуст. Потребитель ожидает...");
                            notEmpty.await();  // Ожидание, если буфер пуст
                        }
                        int item = buffer.poll();  // Потребитель забирает элемент из буфера
                        System.out.println("Потребитель забрал: " + item);
                        notFull.signal();  // Уведомляем производителя, что место в буфере освободилось
                    } finally {
                        lock.unlock();
                    }

                    Thread.sleep(1000);  // Эмуляция времени работы
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Запускаем потоки
        producer.start();
        consumer.start();
    }
}
