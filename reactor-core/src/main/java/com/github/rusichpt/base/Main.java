package com.github.rusichpt.base;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Mono");
        monoExample();
        System.out.println("Flux");
        fluxExample();
        System.out.println("Обработка ошибок");

        Thread.sleep(1000L);
    }

    public static void monoExample() {
        Mono<String> monoExample = Mono.just("Hello World")
                .map(String::toUpperCase) // Преобразование строки в верхний регистр
                .filter(value -> value.length() > 5); // Фильтрация по длине строки

        monoExample.subscribe(
                value -> System.out.println("Получено: " + value),
                error -> System.err.println("Ошибка: " + error),
                () -> System.out.println("Завершено без ошибок"));

        System.out.println();

        // Пустой Mono
        Mono<Void> emptyMono = Mono.empty();

        monoExample.subscribe(
                value -> System.out.println("Получено: " + value),
                error -> System.err.println("Ошибка: " + error),
                () -> System.out.println("Завершено без ошибок"));

        System.out.println();

        // Mono с ошибкой
        Mono<String> errorMono = Mono.error(new RuntimeException("Ошибка!"));

        errorMono.subscribe(
                value -> System.out.println("Получено: " + value),
                error -> System.err.println("Ошибка: " + error),
                () -> System.out.println("Завершено без ошибок"));

        System.out.println();

    }

    public static void fluxExample() throws InterruptedException {
        Flux<String> fluxExample = Flux.just("A", "B", "C");

        // Пустой Flux
        Flux<String> emptyFlux = Flux.empty();

        // Flux из массива
        Flux<Integer> fluxFromArray = Flux.fromArray(new Integer[]{1, 2, 3});

        // Flux с ошибкой
        Flux<String> errorFlux = Flux.error(new RuntimeException("Ошибка!"));

        Flux<Integer> fluxResult = Flux.range(1, 5) // Создание последовательности от 1 до 5
                .map(value -> value * 2) // Умножение каждого элемента на 2
                .filter(value -> value > 5); // Фильтрация элементов больше 5

        fluxResult.subscribe(
                value -> System.out.println("Получено: " + value),
                error -> System.err.println("Ошибка: " + error),
                () -> System.out.println("Завершено"));


        Flux.just("A", "B", "C")
                .flatMap(value -> Mono.just(value + " async"))
                .subscribe(System.out::println);

        System.out.println();
    }



}