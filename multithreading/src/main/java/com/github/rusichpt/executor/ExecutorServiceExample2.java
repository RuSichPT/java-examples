package com.github.rusichpt.executor;

import java.util.concurrent.*;

public class ExecutorServiceExample2 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newCachedThreadPool();

        Future<?> future = executorService.submit(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName());
                Thread.sleep(100);
            }
            return 50;
        });

        while (!future.isDone()) {
            System.out.println("make something until wait result");
            Thread.sleep(50);
        }
        System.out.println("Result :" + future.get()); // future.get()
        executorService.shutdown();
    }
}
