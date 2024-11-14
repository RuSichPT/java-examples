package com.github.rusichpt.problem;

public class Problem1 {
    // Проблема № 1: несогласованные переменные
    public static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    public static void main(String[] args) {

        Point p = new Point(0, 0);

        Thread t1 = new Thread(() -> {
            p.x = 100;
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            p.y = 100;
        });

        Thread t2 = new Thread(() -> {
            System.out.println(p);
        });

        t1.start();
        t2.start();
    }
}

