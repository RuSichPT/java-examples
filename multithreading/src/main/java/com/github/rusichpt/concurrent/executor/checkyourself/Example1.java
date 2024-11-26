package com.github.rusichpt.concurrent.executor.checkyourself;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Example1 {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++) {
            final int value = i;
            executor.submit(() -> System.out.println(value + " " + Thread.currentThread().getName()));
        }
        executor.shutdown();
        // каждая задача выполняется в своём потоке. Так как потоки независимы, порядок чисел в консоли довольно хаотичный
    }
}
