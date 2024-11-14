package com.github.rusichpt.thread;

public class CreateThreads {
    public static void main(String[] args) {

        Runnable task1 = () -> {
            for (int i = 1; i <= 5; i++) {
                try {
                    Thread.sleep(10);
                    System.out.println(i + " " + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        Thread first = new Thread(task1);
        first.setName("First thread");

        Thread second = new MyThread();
        second.setName("Second thread");

        first.start();
        second.start();
    }
}