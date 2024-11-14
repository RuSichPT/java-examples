package com.github.rusichpt.problem.checkyourself;

import java.util.List;
import java.util.stream.IntStream;

public class Example2 {
    private static Integer value = 0;

    public static void main(String[] args) throws InterruptedException {

        List<Thread> threads = IntStream.range(0, 1000).boxed().map(i -> {
            final int v = i;
            return new Thread(() -> increment(v));
        }).toList();

        threads.forEach(Thread::start);

        Thread.sleep(2000);
        System.out.println(value);
        // Число <= 1000 поскольку синхронизация идёт по разным объектам, то синхронизации как бы и нет:)
        // Потоки вмешиваются в работу друг друга, и в итоге несколько апдейтов теряются
    }

    public static void increment(Integer i) {
        synchronized (i) {
            value = value + 1;
        }
    }
}
