package com.github.rusichpt.producerconsumer;

import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumerExample2 {
    private static Queue<String> queue = new LinkedList<>();

    private static final int BUFFER_SIZE = 5;

    private static final Object MONITOR = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread producer = new Thread(() -> {
            try {
                while (true) {
                    synchronized (MONITOR) {
                        if (queue.size() == BUFFER_SIZE) {
                            System.out.println("Буфер полный. Производитель ожидает...");
                            MONITOR.wait();
                        }
                        int item = (int) (Math.random() * 100);
                        System.out.println("Производитель добавил: " + item);
                        queue.add(String.valueOf(item));
                        MONITOR.notify();
                    }
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread consumer = new Thread(() -> {
            try {
                while (true) {
                    synchronized (MONITOR) {
                        if (queue.isEmpty()) {
                            System.out.println("Буфер пуст. Потребитель ожидает...");
                            MONITOR.wait();
                        }
                        String item = queue.poll();
                        System.out.println("Потребитель забрал: " + item);
                        MONITOR.notify();
                    }
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        producer.start();
        consumer.start();
    }
}
