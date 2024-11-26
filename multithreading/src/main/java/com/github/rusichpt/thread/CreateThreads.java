package com.github.rusichpt.thread;

public class CreateThreads {
    public static void main(String[] args) {

        Runnable task1 = () -> {
            for (int i = 1; i < 6; i++) {
                try {
                    Thread.sleep(10);
                    System.out.println(Thread.currentThread().getName() + " " + i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        Thread first = new Thread(task1, "First thread");
        Thread second = new MyThread("Second thread");

        first.start();
        second.start();
    }
}