package com.github.rusichpt.executor;

import java.util.concurrent.*;

public class ExecutorServiceExample1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(3);

        service.execute(() -> System.out.println("Another thread was executed"));

        Future<String> future = service.submit(() -> {
            System.out.println("Another thread was executed");
            Thread.sleep(500);
            return "result";
        });

        System.out.println("Result: " + future.get()); // future.get() block Thread

        service.shutdown();
    }
}
