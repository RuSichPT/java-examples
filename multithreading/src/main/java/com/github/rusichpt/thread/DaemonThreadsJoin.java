package com.github.rusichpt.thread;

import lombok.SneakyThrows;

public class DaemonThreadsJoin {
    @SneakyThrows
    public static void main(String[] args) {
        Thread thread1 = new MyThread();
        Thread thread2 = new MyThread();
        thread1.setDaemon(true);
        thread2.setDaemon(true);

        thread1.start();
        thread2.start();

        thread1.join();// Ожидает завершения этого потока.
        thread2.join();// Ожидает завершения этого потока.
    }
}
