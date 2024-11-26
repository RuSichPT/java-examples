package com.github.rusichpt.concurrent.collection;

import java.util.concurrent.ConcurrentSkipListSet;

public class ConcurrentSkipListSetExample {
    public static void main(String[] args) {
        // Создание ConcurrentSkipListSet
        ConcurrentSkipListSet<Integer> set = new ConcurrentSkipListSet<>();

        // Добавление элементов
        set.add(10);
        set.add(20);
        set.add(5);
        set.add(15);

        System.out.println("Начальное содержимое множества: " + set);

        // Проверка наличия элемента
        System.out.println("Содержит 10? " + set.contains(10));

        // Удаление элемента
        set.remove(5);
        System.out.println("После удаления 5: " + set);

        // Методы поиска ближайших элементов
        System.out.println("Элемент выше 10: " + set.higher(10));
        System.out.println("Элемент ниже 10: " + set.lower(10));

        // Работа с подмножествами
        System.out.println("Элементы от 10 до 20: " + set.subSet(10, true, 20, false));
        System.out.println("Элементы до 15: " + set.headSet(15));
        System.out.println("Элементы от 15 и выше: " + set.tailSet(15));

        // Многопоточная работа
        Thread thread1 = new Thread(() -> set.add(25));
        Thread thread2 = new Thread(() -> set.add(30));

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Итоговое содержимое множества: " + set);
    }
}
