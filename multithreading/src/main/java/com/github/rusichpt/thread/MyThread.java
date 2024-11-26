package com.github.rusichpt.thread;

import lombok.SneakyThrows;

public class MyThread extends Thread {
    public MyThread(String name) {
        super(name);
    }

    public MyThread() {
    }

    @SneakyThrows
    @Override
    public void run() {
        for (int i = 10; i < 15; i++) {
            Thread.sleep(10);
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }
}
