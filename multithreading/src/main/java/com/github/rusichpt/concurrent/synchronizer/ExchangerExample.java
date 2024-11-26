package com.github.rusichpt.concurrent.synchronizer;

import java.util.concurrent.Exchanger;

public class ExchangerExample {
    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();

        // Первый поток
        Thread thread1 = new Thread(() -> {
            try {
                String message = "Привет от потока 1!";
                System.out.println("Поток 1 отправляет сообщение: " + message);
                String reply = exchanger.exchange(message); // Обмен
                System.out.println("Поток 1 получил сообщение: " + reply);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Второй поток
        Thread thread2 = new Thread(() -> {
            try {
                String message = "Привет от потока 2!";
                System.out.println("Поток 2 отправляет сообщение: " + message);
                String reply = exchanger.exchange(message); // Обмен
                System.out.println("Поток 2 получил сообщение: " + reply);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Запускаем потоки
        thread1.start();
        thread2.start();
    }
}
