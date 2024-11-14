package com.github.rusichpt.thread;

import lombok.SneakyThrows;

import java.util.stream.IntStream;

public class InterruptedThread extends Thread {
    @Override
    @SneakyThrows
    public void run() {
        while (!this.isInterrupted()) {
            System.out.println("work");
            for (long i = 0; i < 100_000_000L; i++) {

            }
        }
    }

    @SneakyThrows
    public static void main(String[] args) {
        Thread interruptedThread = new InterruptedThread();

        interruptedThread.start();
        Thread.sleep(200);
        interruptedThread.interrupt();

        // Если код не обрабатывает прерывания (isInterrupted()), бесполезно использовать метод interrupt
        // Ни один класс Stream API не проверяет флажок прерывания
        Thread t = new Thread(() -> {
            IntStream.range(0, 1000).boxed().forEach(
                    System.out::println
            );
        });

        t.start();
        t.interrupt();
    }
}
