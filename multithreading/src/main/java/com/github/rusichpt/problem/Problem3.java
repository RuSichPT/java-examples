package com.github.rusichpt.problem;

public class Problem3 {
    // Проблема №3: потоки не видят изменений, сделанных другими потоками

    // Общая переменная, которую один поток меняет, а другой - читает
    public static int value;

    public static void main(String[] args) {

        // Читаем значение переменной
        Thread reader = new Thread(() -> {
            int temp = 0;
            while (true) {
                if (temp != value) {
                    temp = value;
                    System.out.println("value = " + value);
                }
            }
        });
        reader.setDaemon(true); // чтобы завершить программу после выполнения writer

        // Обновляем значение переменной
        Thread writer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                value++;
                System.out.println("value updated: " + value);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            // Немного ждём
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        reader.start();
        writer.start();

    }
}
