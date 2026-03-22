package com.github.rusichpt;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("all")
class TaggedTest {
    // @Tag — позволяет навешивать метки на тесты и потом выборочно запускать или исключать нужные группы через MAven / Gradle


    @Test
    @Tag("fast")
    void fastTest() {
        // Быстрый тест
    }

    @Test
    @Tag("slow")
    void slowTest() {
        // Медленный тест
    }

    @Test
    @Tag("integration")
    void integrationTest() {
        // Интеграционный тест
    }
}
