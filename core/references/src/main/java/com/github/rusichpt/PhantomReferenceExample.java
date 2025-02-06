package com.github.rusichpt;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.List;

public class PhantomReferenceExample {
    public static void main(String[] args) throws InterruptedException {
        // Очередь для фантомных ссылок
        ReferenceQueue<MyObject> queue = new ReferenceQueue<>();

        // Создаем объект
        MyObject obj = new MyObject();

        // Создаем фантомную ссылку, привязанную к ReferenceQueue
        PhantomReference<MyObject> phantomRef = new PhantomReference<>(obj, queue);

        // Проверяем, доступен ли объект через фантомную ссылку
        System.out.println("Before GC: " + phantomRef.get()); // Всегда null

        // Удаляем сильную ссылку и вызываем GC
        obj = null;
        System.gc(); // Запрос на сборку мусора
        Thread.sleep(100); // Даем GC немного времени для срабатывания

        // Принудительная финализация объектов (не обязательно, но может помочь)
        System.runFinalization();

        // Даем время для завершения работы GC
        Thread.sleep(500); // Увеличено время ожидания для гарантии выполнения GC

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < Integer.MAX_VALUE/20; i++) {
            list.add(i);
        }
        list = null;
        System.gc(); // Запрос на сборку мусора
        Thread.sleep(500); // Увеличено время ожидания для гарантии выполнения GC

        // Проверяем, попала ли фантомная ссылка в очередь
        Reference<? extends MyObject> refFromQueue = queue.poll(); // Используем poll, чтобы избежать блокировки
        if (refFromQueue != null) {
            System.out.println("Object is ready for cleanup");
            // Можно выполнить дополнительные действия, например, освободить ресурсы
        } else {
            System.out.println("Phantom reference not yet in queue.");
        }
    }

    static class MyObject {
        @Override
        protected void finalize() throws Throwable {
            System.out.println("Finalizing MyObject...");
        }
    }
}
