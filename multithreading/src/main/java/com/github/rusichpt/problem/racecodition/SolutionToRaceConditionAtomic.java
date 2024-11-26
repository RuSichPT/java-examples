package com.github.rusichpt.problem.racecodition;

import java.util.concurrent.atomic.AtomicInteger;

public class SolutionToRaceConditionAtomic {
    // Проблема RaceCondition (Состояние гонки)
    // Проблема №2: потерянный апдейт

    // Решение Atomic

    public static class Counter {
        private final AtomicInteger count = new AtomicInteger(0);

        public void increment() {
            count.incrementAndGet();
        }

        public int getCount() {
            return count.get();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                counter.increment();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                counter.increment();
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("count = " + counter.getCount());
    }
}
