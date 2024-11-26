package com.github.rusichpt.concurrent.collection;

import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListExample {
    public static void main(String[] args) {
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();

        // Добавление элементов
        list.add("A");
        list.add("B");
        list.add("C");

        // Итерация (без блокировок, даже если список модифицируется)
        for (String item : list) {
            System.out.println(item);

            // Модификация списка
            list.add("D"); // Создается новая копия массива
        }

        System.out.println("Список после модификации: " + list);
    }
}
