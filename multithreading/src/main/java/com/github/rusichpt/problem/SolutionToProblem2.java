package com.github.rusichpt.problem;

public class SolutionToProblem2 {
    // Проблема №2: потерянный апдейт
    // Решение synchronized - теперь в каждый момент времени только один поток работает со счётчиком. В консоли мы увидим правильное значение:
    // Правильнее добавить volatile count, но в данном прмиере нет необходимости
    // Или synchronized getCount()
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


    public static void main(String[] args) throws InterruptedException {
        SolutionToProblem2 solution = new SolutionToProblem2();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                solution.increment();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                solution.increment();
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("count = " + solution.getCount());
    }
}
