package com.github.rusichpt.problem;

public class Problem2 {
    // Проблема №2: потерянный апдейт
    private int count = 0;

    public void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }

    public static void main(String[] args) throws InterruptedException {
        Problem2 problem = new Problem2();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                problem.increment();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                problem.increment();
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("count = " + problem.getCount());
    }
}
