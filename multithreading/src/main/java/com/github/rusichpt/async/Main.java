package com.github.rusichpt.async;

import java.util.concurrent.CompletableFuture;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        CompletableFuture.runAsync(() -> System.out.println("Задача выполняется в потоке: " + Thread.currentThread().getName()));

        CompletableFuture<Double> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("Задача выполняется в потоке: " + Thread.currentThread().getName());
            return Math.random();
        });

        future.thenApply(aDouble -> {
                    System.out.println("Задача выполняется в потоке: " + Thread.currentThread().getName());
                    return String.valueOf(aDouble);
                })
                .thenAccept(System.out::println)
                .thenAccept(unused -> {
                    throw new RuntimeException("test");
                })
                .exceptionally(throwable -> {
                    System.out.println("Error: " + throwable.getMessage());
                    return null;
                });

        Thread.sleep(1000);
        System.out.println("Конец Main");
    }
}
