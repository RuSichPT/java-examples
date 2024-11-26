package com.github.rusichpt.concurrent.executor;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinPollExample1 {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        ForkJoinPool pool = new ForkJoinPool();

        SumTask task = new SumTask(array, 0, array.length);
        int result = pool.invoke(task);

        System.out.println("Sum: " + result);
    }

    public static class SumTask extends RecursiveTask<Integer> {
        private final int[] array;
        private final int start, end;
        private static final int THRESHOLD = 3; // Порог разделения
        private static int count = 0;

        public SumTask(int[] array, int start, int end) {
            this.array = array;
            this.start = start;
            this.end = end;
            System.out.println("Задача " + ++count + " создана");
        }

        @Override
        protected Integer compute() {
            if (end - start <= THRESHOLD) {
                // Базовый случай: просто суммируем элементы
                int sum = 0;
                for (int i = start; i < end; i++) {
                    sum += array[i];
                }
                return sum;
            } else {
                // Разделяем задачу на две части
                int mid = (start + end) / 2;
                SumTask leftTask = new SumTask(array, start, mid);
                SumTask rightTask = new SumTask(array, mid, end);

                // Рекурсивно выполняем подзадачи
                leftTask.fork(); // Асинхронно выполняем левую подзадачу
                int rightResult = rightTask.compute(); // Выполняем правую подзадачу
                int leftResult = leftTask.join(); // Ожидаем завершения левой

                // Объединяем результаты
                return leftResult + rightResult;
            }
        }
    }
}

