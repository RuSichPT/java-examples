package com.github.rusichpt.concurrent.executor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceExample3 {
    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(3);

        service.schedule(() -> System.out.println("Запуск после 2 секунд"),2, TimeUnit.SECONDS);

        service.scheduleAtFixedRate(() -> System.out.println("Запуск каждую секунду"),0, 1, TimeUnit.SECONDS);

        service.scheduleWithFixedDelay(() -> System.out.println("Между запусками 1 секунда"),0, 1, TimeUnit.SECONDS);

        Thread.sleep(10000);
        service.shutdown();
    }
}
