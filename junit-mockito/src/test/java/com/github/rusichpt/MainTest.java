package com.github.rusichpt;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class MainTest {
    @BeforeAll
    static void setup() {
        log.info("@BeforeAll - выполняется один раз перед всеми тестами");
    }

    @BeforeEach
    void init() {
        log.info("@BeforeEach - выполняется перед каждым тестом");
    }

    @DisplayName("Single test successful")
    @Test
    void testSingleSuccessTest() {
        log.info("Success");
    }

    @Test
    @Disabled("Not implemented yet")
    void testShowSomething() {
    }

    @Test
    void lambdaExpressions() {
        List<Integer> numbers = Arrays.asList(1, 2, 3);
        assertTrue(numbers.stream()
                .mapToInt(Integer::intValue)
                .sum() > 5, () -> "Sum should be greater than 5");
    }

    @Test
    void groupAssertions() {
        int[] numbers = {0, 1, 2, 3, 4};
        assertAll("numbers",
                () -> assertEquals(1, numbers[1]),
                () -> assertEquals(3, numbers[3]),
                () -> assertEquals(4, numbers[4])
        );
    }

    @AfterEach
    void afterEach() {
        log.info("@AfterEach - выполняется после каждого теста");
    }

    @AfterAll
    static void afterAll() {
        log.info("AfterAll - выполняется один раз после всех тестов");
    }
}