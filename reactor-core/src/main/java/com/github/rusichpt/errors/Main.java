package com.github.rusichpt.errors;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Main {
    public static void main(String[] args) {
        handleErrors();
    }
    public static void handleErrors() {
        System.out.println("C прерыванием потока");
        Flux<String> errorFlux = Flux.just("1", "2", "oops", "3")
                .map(value -> {
                    if ("oops".equals(value)) throw new RuntimeException("Ошибка!");
                    return value;
                })
                .onErrorResume(e -> Flux.just("default"));

        errorFlux.subscribe(
                value -> System.out.println("Получено: " + value),
                error -> System.err.println("Ошибка: " + error),
                () -> System.out.println("Завершено"));
        System.out.println();

        System.out.println("Без прерывания потока");
        errorFlux = Flux.just("1", "2", "oops", "3")
                .flatMap(s -> Mono.just(s)
                        .map(value -> {
                            if ("oops".equals(value)) throw new RuntimeException("Ошибка!");
                            return value;
                        })
                        .onErrorResume(e -> Mono.just("default")));

        errorFlux.subscribe(
                value -> System.out.println("Получено: " + value),
                error -> System.err.println("Ошибка: " + error),
                () -> System.out.println("Завершено"));

        System.out.println();
    }
}
