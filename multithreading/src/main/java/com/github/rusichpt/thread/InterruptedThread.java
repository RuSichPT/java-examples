package com.github.rusichpt.thread;

import lombok.SneakyThrows;

import java.util.stream.IntStream;

public class InterruptedThread extends Thread {
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("Thread is working...");
            try {
                Thread.sleep(500); // Симуляция работы
            } catch (InterruptedException e) {
                // Поток прерывается во время sleep, нужно завершить работу
                System.out.println("Thread interrupted during sleep, stopping...");
                break;
            }
        }
        System.out.println("Thread stopped.");
    }

    @SneakyThrows
    public static void main(String[] args) {
        // Создаем поток, который проверяет флаг прерывания
        Thread interruptedThread = new InterruptedThread();

        interruptedThread.start(); // Запускаем поток

        Thread.sleep(2000); // Основной поток ждёт 2 секунды
        System.out.println("Main: Requesting thread to stop...");
        interruptedThread.interrupt(); // Прерываем поток
        interruptedThread.join(); // Ждем завершения потока
        System.out.println("Main: Thread has stopped.");

        System.out.println("/////////////////////////////////////////");
        /////////////////////////////////////////
        // Если код не обрабатывает прерывания (isInterrupted()), бесполезно использовать метод interrupt
        // Ни один класс Stream API не проверяет флажок прерывания
        Thread t = new Thread(() -> {
            IntStream.range(0, 10).boxed().forEach(
                    System.out::println
            );
        });

        t.start(); // Запускаем поток
        System.out.println("Main: Requesting thread to stop...");
        t.interrupt(); // Ждем завершения потока
        t.join(); // Ждем завершения потока
        System.out.println("Main: Thread has stopped.");
    }
}
