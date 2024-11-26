package com.github.rusichpt.concurrent.collection;

import java.util.concurrent.CopyOnWriteArraySet;

public class CopyOnWriteArraySetExample {
    public static void main(String[] args) {
        CopyOnWriteArraySet<Integer> set = new CopyOnWriteArraySet<>();

        // Добавление элементов
        set.add(1);
        set.add(2);
        set.add(3);

        // Итерация (без блокировок, даже если список модифицируется)
        for (Integer item : set) {
            System.out.println(item);

            // Модификация списка
            set.add(4); // Создается новая копия массива
        }

        System.out.println("Список после модификации: " + set);
    }
}
