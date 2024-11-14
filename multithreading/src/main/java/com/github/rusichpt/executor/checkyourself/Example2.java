package com.github.rusichpt.executor.checkyourself;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Example2 {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 5; i++) {
            final int value = i;
            executor.submit(() -> System.out.println(value));
        }
        // 0 1 2 3 тк в экзекьюторе всего один поток, поэтому задачи выполняются последовательно
    }
}
