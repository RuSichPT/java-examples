package com.github.rusichpt;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AssertionsTest {

    @Test
    void testAssertions() {
        // Базовые assertions
        assertEquals(4, 2 + 2);
        assertNotEquals(5, 2 + 2);
        assertTrue(5 > 3);
        assertFalse(3 > 5);
        assertNull(null);
        assertNotNull("hello");

        // С сообщением об ошибке
        assertEquals(5, 2 + 3, "Математика сломалась!");

        // Ленивое сообщение (вычисляется только при ошибке)
        assertEquals(5, 2 + 3, () -> "Сложное сообщение: " + System.currentTimeMillis());

        // Проверка массивов
        int[] expected = {1, 2, 3};
        int[] actual = {1, 2, 3};
        assertArrayEquals(expected, actual);

        // Проверка с плавающей точкой
        assertEquals(0.3, 0.1 + 0.2, 0.0001);

        // Проверка исключений
        Exception exception = assertThrows(ArithmeticException.class, () -> {
            int result = 10 / 0;
        });
        assertEquals("/ by zero", exception.getMessage());

        // Проверка, что код не выбрасывает исключение
        assertDoesNotThrow(() -> {
            int result = 10 / 2;
        });

        // Проверка Optional
        Optional<String> optional = Optional.of("value");
        assertTrue(optional.isPresent());
        assertEquals("value", optional.get());

        // Группировка assertion'ов
        assertAll("user",
                () -> assertEquals("John", "John"),
                () -> assertEquals("Doe", "Doe"),
                () -> assertTrue(25 > 18)
        );
    }
}
