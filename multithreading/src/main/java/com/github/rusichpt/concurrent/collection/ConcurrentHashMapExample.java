package com.github.rusichpt.concurrent.collection;

import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapExample {
    public static void main(String[] args) {
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
        HashMap<String, Integer> hashMap = new HashMap<>();

        // Добавление элементов
        map.put("One", 1);
        map.put("Two", 2);
        map.putIfAbsent("Three", 3);

        hashMap.putIfAbsent("Three", 3);

        System.out.println("Initial Map: " + map);

        // Изменение значений
        map.replace("Two", 22);
        map.computeIfAbsent("Four", key -> 4); // Вычисляет значение только если ключ отсутствует
        map.computeIfPresent("One", (key, val) -> val + 10); // Изменяет значение только если ключ есть

        System.out.println("Updated Map: " + map);

        // Удаление элементов
        map.remove("Two", 2); // Не удалит, так как значение не совпадает
        map.remove("Two", 22); // Удалит
        System.out.println("After Removal: " + map);

        // Параллельная обработка
        map.forEach(1, (key, val) -> System.out.println(key + " -> " + val));

        Set<String> newKeySet = ConcurrentHashMap.newKeySet();
    }
}
