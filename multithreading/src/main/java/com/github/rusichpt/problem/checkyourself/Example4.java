package com.github.rusichpt.problem.checkyourself;

public class Example4 {
    public abstract static class AbstractParent {

        public static synchronized void runParent() { // static
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

        public static synchronized void runChild() { // static
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

        new Thread(Child::runChild).start();
        new Thread(Child::runParent).start();

        // Child start Parent start Parent end Child end
        // Parent start Child start Parent end Child end
        // потоки синхронизируются по разным объектам (AbstractParent.class и Child.class), поэтому синхронизации как бы и нет.
        // Каждый поток последовательно печатает start и end, но в каком порядке потоки завладеют консолью - неизвестно. Поэтому вариантов несколько
    }
}
