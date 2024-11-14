package com.github.rusichpt.problem.checkyourself;

import java.util.List;
import java.util.stream.IntStream;

public class Example1 {
    private static final Object lock = new Object();
    private static Integer value = 0;

    public static void main(String[] args) throws InterruptedException {

        List<Thread> threads = IntStream.range(0, 1000).boxed().map(i -> {
            final int v = i;
            return new Thread(() -> increment(v));
        }).toList();

        threads.forEach(Thread::start);

        Thread.sleep(2000);
        System.out.println(value);
        // 1000 - synchronized секция пускает только один поток в критическую секцию. Поэтому потоки не мешают друг другу выполнить инкремент
    }

    public static void increment(Integer i) {
        synchronized (lock) {
            value = value + 1;
        }
    }
}
