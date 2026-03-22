package com.github.rusichpt.junit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.*;

public class AssumptionTest {

    @Test
    void trueAssumption() {
        assumeTrue(5 > 1);
        assertEquals(5 + 2, 7);
    }

    @Test
    void falseAssumption() {
        assumeFalse(5 < 1);
        assertEquals(5 + 2, 7);
    }

    @Test
    void assumptionThat() {
        String someString = "Just a string";
        assumingThat(
                someString.equals("1Just a string"),
                () -> assertEquals(2 + 1, 4)
        );
    }

    @Test
    void testWithAssumptions() {
        String env = System.getenv("TEST_ENV");

        // Тест выполняется только если условие истинно
        assumeTrue("dev".equals(env));

        // Или с сообщением
        assumeTrue("prod".equals(env), "Тест только для production");

        // Тест выполняется только если условие ложно
        assumeFalse("test".equals(env));

        // Более гибкий вариант
        assumingThat("dev".equals(env),
                () -> {
                    // Этот код выполнится только в dev окружении
                    assertEquals(5, 2 + 3);
                }
        );

        // Этот код выполнится всегда
        assertEquals(4, 2 + 2);
    }
}
