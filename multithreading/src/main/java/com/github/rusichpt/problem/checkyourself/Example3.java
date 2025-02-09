package com.github.rusichpt.problem.checkyourself;

public class Example3 {
    public abstract static class AbstractParent {

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

        // Child start Child end Parent start Parent end
        // при заходе в synchronized метод поток захватывает монитор объекта child, поэтому методы выполняются последовательно
    }
}
