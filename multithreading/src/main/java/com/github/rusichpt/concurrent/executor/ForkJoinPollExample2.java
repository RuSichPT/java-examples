package com.github.rusichpt.concurrent.executor;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ForkJoinPollExample2 {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        ForkJoinPool pool = new ForkJoinPool();

        IncrementTask task = new IncrementTask(array, 0, array.length);
        pool.invoke(task);

        for (int num : array) {
            System.out.print(num + " ");
        }
    }

    public static class IncrementTask extends RecursiveAction {
        private final int[] array;
        private final int start, end;
        private static final int THRESHOLD = 3;
        private static int count;

        public IncrementTask(int[] array, int start, int end) {
            this.array = array;
            this.start = start;
            this.end = end;
            System.out.println("Задача " + ++count + " создана");
        }

        @Override
        protected void compute() {
            if (end - start <= THRESHOLD) {
                for (int i = start; i < end; i++) {
                    array[i]++;
                }
            } else {
                int mid = (start + end) / 2;
                IncrementTask leftTask = new IncrementTask(array, start, mid);
                IncrementTask rightTask = new IncrementTask(array, mid, end);

                invokeAll(leftTask, rightTask);
            }
        }
    }
}
