package com.github.rusichpt.thread;

import lombok.SneakyThrows;

public class MyThread extends Thread {
    @SneakyThrows
    @Override
    public void run() {
        for (int i = 10; i <= 14; i++) {
            Thread.sleep(10);
            System.out.println(i + " " + Thread.currentThread().getName());
        }
    }
}
