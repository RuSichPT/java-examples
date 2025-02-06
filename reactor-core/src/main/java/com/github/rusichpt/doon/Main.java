package com.github.rusichpt.doon;

import reactor.core.publisher.Flux;

public class Main {
    public static void main(String[] args) {
        Flux.range(1, 5)
                .doOnSubscribe(subscription -> System.out.println("Подписка началась"))
                .doOnNext(item -> System.out.println("Получен элемент: " + item))
                .map(i -> {
                    System.out.println("Поток " + Thread.currentThread().getName());
                    if (i == 3)
                        throw new RuntimeException("Ошибка");
                    return i;
                })
                .doOnComplete(() -> System.out.println("Поток завершен")) // Вызывается, когда поток успешно завершился.
                .doOnError(error -> System.err.println("Произошла ошибка: " + error.getMessage()))
                .doOnTerminate(() -> System.out.println("Поток завершился (успешно или с ошибкой)"))
                .doOnRequest(request -> System.out.println("Запрошено элементов: " + request))
                .doFinally(signalType -> System.out.println("Поток завершился: " + signalType)) // Вызывается в самом конце, независимо от результата выполнения (onComplete, onError, onCancel).
                .subscribe();

        System.out.println("end Main");
    }
}
