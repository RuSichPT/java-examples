package com.github.rusichpt;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public class WeakReferenceQueueExample {
    public static void main(String[] args) throws InterruptedException {
        // Очередь для слабых ссылок
        ReferenceQueue<MyObject> queue = new ReferenceQueue<>();

        // Создаем объект
        MyObject obj = new MyObject();

        // Создаем слабую ссылку, привязанную к ReferenceQueue
        WeakReference<MyObject> weakRef = new WeakReference<>(obj, queue);

        // Проверяем, доступен ли объект через weakRef
        System.out.println("Before GC: " + (weakRef.get() != null)); // true

        // Удаляем сильную ссылку и вызываем GC
        obj = null;
        System.gc();
        System.gc();
        System.runFinalization();
        Thread.sleep(100); // Даем GC время на срабатывание

        // Проверяем, удалился ли объект
        System.out.println("After GC: " + (weakRef.get() != null)); // false (объект удален)

        // Проверяем, попала ли слабая ссылка в очередь
        Reference<? extends MyObject> refFromQueue = queue.poll();
        System.out.println("Is in ReferenceQueue: " + (refFromQueue != null)); // true
    }

    static class MyObject {
        @Override
        protected void finalize() throws Throwable {
            System.out.println("Finalizing MyObject...");
        }
    }
}
