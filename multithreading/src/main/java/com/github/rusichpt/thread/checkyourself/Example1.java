package com.github.rusichpt.thread.checkyourself;

public class Example1 {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(Example1::print);
        Thread t2 = new Thread(Example1::print);

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        // 5 15 5 15
        // каждый поток работает со своей переменной i
    }

    public static void print() {
        int i = 5;
        System.out.println(i);
        i += 10;
        System.out.println(i);
    }
}
