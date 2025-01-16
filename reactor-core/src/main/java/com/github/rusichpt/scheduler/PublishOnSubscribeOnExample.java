package com.github.rusichpt.scheduler;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class PublishOnSubscribeOnExample {
    public static void main(String[] args) throws InterruptedException {
        publishOn();
        Thread.sleep(1000L);
        System.out.println();
        subscribeOn();
        Thread.sleep(1000L);
    }

    public static void subscribeOn() {
        Flux<Integer> flux = Flux.range(1, 5)
                .map(i -> {
                    System.out.println("Sub Генерация: " + i + " в потоке " + Thread.currentThread().getName());
                    return i;
                })
                .subscribeOn(Schedulers.boundedElastic()) // Меняем поток для подписки на источник
                .map(i -> {
                    System.out.println("Sub Обработка: " + i + " в потоке " + Thread.currentThread().getName());
                    return i;
                });

        flux.subscribe(i -> System.out.println("Sub Получение: " + i + " в потоке " + Thread.currentThread().getName()));
    }

    public static void publishOn() {
        Flux<Integer> flux = Flux.range(1, 5)
                .map(i -> {
                    System.out.println("Pub Генерация: " + i + " в потоке " + Thread.currentThread().getName());
                    return i;
                })
                .publishOn(Schedulers.boundedElastic()) // Меняем поток для обработки
                .map(i -> {
                    System.out.println("Pub Обработка: " + i + " в потоке " + Thread.currentThread().getName());
                    return i;
                });

        flux.subscribe(i -> System.out.println("Pub Получение: " + i + " в потоке " + Thread.currentThread().getName()));

    }
}
