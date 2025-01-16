package com.github.rusichpt.scheduler;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class BoundedElasticExample {
    public static void main(String[] args) throws InterruptedException {
        Flux.range(1, 10)
                .map(i -> {
                    System.out.println("Идет обработка числа " + i + " на потоке: " + Thread.currentThread().getName());
                    return i;
                })
                .publishOn(Schedulers.boundedElastic()) // Переключаем выполнение на boundedElastic
                .map(i -> {
                    simulateBlockingOperation();
                    System.out.println("Завершена обработка числа " + i + " на потоке: " + Thread.currentThread().getName());
                    return i;
                })
                .subscribe();

        Thread.sleep(11000L);
    }

    private static void simulateBlockingOperation() {
        try {
            Thread.sleep(1000); // Симуляция блокирующей операции
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
