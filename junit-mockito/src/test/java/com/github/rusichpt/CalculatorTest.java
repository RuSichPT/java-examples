package com.github.rusichpt;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Test
    void testAddition() {
        int result = calculator.add(2, 3);
        assertEquals(5, result, "2 + 3 должно равняться 5");
    }

    @Nested //нужен только для логической группировки и улучшения читаемости тестов
    class AdditionTests {

        @Test
        void testAddPositiveNumbers() {
            assertEquals(5, calculator.add(2, 3));
        }

        @Test
        void testAddNegativeNumbers() {
            assertEquals(-5, calculator.add(-2, -3));
        }

        @ParameterizedTest
        @CsvSource({
                "0, 0, 0",
                "1, 1, 2",
                "10, -5, 5"
        })
        void testAddWithParameters(int a, int b, int expected) {
            assertEquals(expected, calculator.add(a, b));
        }
    }

    @Nested
    class DivisionTests {

        @Test
        void testDividePositiveNumbers() {
            assertEquals(2, calculator.divide(10, 5));
        }

        @Test
        void testDivideByZero() {
            assertThrows(ArithmeticException.class, () -> calculator.divide(10, 0));
        }
    }

    @Test
    @Disabled("Дорабатывается")
    void testNotImplementedYet() {
        // Тест временно отключен
    }

    @Test
    @DisplayName("Сложение двух чисел: 2 + 3 = 5")
    void testWithDisplayName() {
        assertEquals(5, calculator.add(2, 3));
    }
}