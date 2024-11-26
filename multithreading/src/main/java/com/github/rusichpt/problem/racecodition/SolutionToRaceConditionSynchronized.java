package com.github.rusichpt.problem.racecodition;

public class SolutionToRaceConditionSynchronized {
    // Проблема RaceCondition (Состояние гонки)
    // Проблема №2: потерянный апдейт

    // Решение synchronized - теперь в каждый момент времени только один поток работает со счётчиком. В консоли мы увидим правильное значение:
    // Правильнее добавить volatile count, но в данном прмиере нет необходимости
    // Или synchronized getCount()

    public static class Counter {
        private int count = 0;
        private final Object lock = new Object();

        public void increment() {
            synchronized (lock) {
                count++;
            }
        }

        public int getCount() {
            return count;
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
