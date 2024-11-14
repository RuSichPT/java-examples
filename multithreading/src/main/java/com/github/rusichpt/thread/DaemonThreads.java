package com.github.rusichpt.thread;

public class DaemonThreads {
    public static void main(String[] args) {
        Thread thread1 = new MyThread();
        Thread thread2 = new MyThread();
        thread1.setDaemon(true);
        thread2.setDaemon(true);

        thread1.start();
        thread2.start();
        System.out.println("JVM closes, because threads daemons");
        // пока в JVM есть хотя бы один обычный поток, JVM продолжает работу
    }
}
