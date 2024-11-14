package com.github.rusichpt.thread.checkyourself;

public class Example2 {
    public static void print() {
        String name = Thread.currentThread().getName();
        System.out.println(name);
    }

    public static void main(String[] args) throws InterruptedException {
        String name = "Empty";

        Thread t1 = new Thread(Example2::print);
        Thread t2 = new Thread(Example2::print);

        t1.start();
        t1.join();
        t2.start();
        t2.join();
        // Thread-0 Thread-1
        // каждый поток работает со своим объектом name
    }
}
