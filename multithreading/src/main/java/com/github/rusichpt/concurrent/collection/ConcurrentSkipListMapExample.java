package com.github.rusichpt.concurrent.collection;

import java.util.concurrent.ConcurrentSkipListMap;

public class ConcurrentSkipListMapExample {
    public static void main(String[] args) {
        // Создание ConcurrentSkipListMap
        ConcurrentSkipListMap<Integer, String> map = new ConcurrentSkipListMap<>();

        // Добавление элементов
        map.put(10, "Ten");
        map.put(20, "Twenty");
        map.put(5, "Five");

        System.out.println("Начальное содержимое карты: " + map);

        // Получение значения по ключу
        System.out.println("Значение для ключа 10: " + map.get(10));

        // Поиск ближайших ключей
        System.out.println("Ключ больше 10: " + map.higherKey(10));
        System.out.println("Ключ меньше 10: " + map.lowerKey(10));

        // Работа с диапазонами
        System.out.println("Элементы до ключа 20: " + map.headMap(20));
        System.out.println("Элементы от ключа 10 и выше: " + map.tailMap(10));

        // Удаление элемента
        map.remove(5);
        System.out.println("После удаления ключа 5: " + map);

        // Многопоточное использование
        Thread thread1 = new Thread(() -> map.put(15, "Fifteen"));
        Thread thread2 = new Thread(() -> System.out.println("Карта в потоке 2: " + map));

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Итоговое содержимое карты: " + map);
    }
}
