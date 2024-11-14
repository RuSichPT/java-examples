package com.github.rusichpt.thread.checkyourself;

public class CustomThread2 extends Thread {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new CustomThread2();
        thread.run();
        thread.start();
        Thread.sleep(1000);
        thread.run();
        thread.start();

        //main Thread-0 main IllegalThreadStateException
    }
}
