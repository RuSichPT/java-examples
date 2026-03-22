package com.github.rusichpt.junit;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class ParameterizedTests {
    @ParameterizedTest
    @ValueSource(strings = {"racecar", "radar", "madam"})
    void testPalindromes(String word) {
        assertTrue(isPalindrome(word));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    void testNumbers(int number) {
        assertTrue(number > 0);
    }

    @ParameterizedTest
    @CsvSource({
            "2, 3, 5",
            "10, 20, 30",
            "0, 0, 0"
    })
    void testAddition(int a, int b, int expected) {
        assertEquals(expected, a + b);
    }

//    @ParameterizedTest
//    @CsvFileSource(resources = "/test-data.csv", numLinesToSkip = 1)
    void testWithCsvFile(String input, int expected) {
        // Тест с данными из CSV файла
    }

    @ParameterizedTest
    @EnumSource(TimeUnit.class)//Проще говоря — это быстрый способ прогнать тест со всеми значениями enum'а.
    void testWithEnum(TimeUnit timeUnit) {
        log.info(timeUnit.name());
        assertNotNull(timeUnit);
    }

    @ParameterizedTest
    @MethodSource("provideNumbers")
    void testWithMethodSource(int number) {
        assertTrue(number > 0);
    }

    private static Stream<Integer> provideNumbers() {
        return Stream.of(1, 2, 3, 4, 5);
    }

    private boolean isPalindrome(String word) {
        return new StringBuilder(word).reverse().toString().equals(word);
    }
}
