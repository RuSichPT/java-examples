package com.github.rusichpt.problem.checkyourself;

public class Example4 {
    public static abstract class AbstractParent {

        public synchronized void runParent() {
            System.out.println("Parent start");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Parent end");
        }
    }

    public static class Child extends AbstractParent {

        public synchronized void runChild() {
            System.out.println("Child start");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Child end");
        }
    }

    public static void main(String[] args) {

        Child child = new Child();

        new Thread(child::runChild).start();
        new Thread(child::runParent).start();

        // Child start Parent start Parent end Child end
        // Parent start Child start Parent end Child end
        // потоки синхронизируются по разным объектам (AbstractParent.class и Child.class), поэтому синхронизации как бы и нет.
        // Каждый поток последовательно печатает start и end, но в каком порядке потоки завладеют консолью - неизвестно. Поэтому вариантов несколько
    }
}
