package com.github.rusichpt.awaitility;

import org.awaitility.Awaitility;
import org.awaitility.Duration;
import org.awaitility.proxy.AwaitilityClassProxy;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;
import static org.awaitility.Awaitility.given;

class AsyncServiceTest {
    private AsyncService asyncService;

    @BeforeEach
    void setUp() {
        asyncService = new AsyncService();
    }

    @Test
    void initializeTest1() {
        // Можно изменить настройки по умолчанию
        Awaitility.setDefaultPollInterval(10, TimeUnit.MILLISECONDS); //100мс
        Awaitility.setDefaultPollDelay(Duration.ZERO); // 100 мс
        Awaitility.setDefaultTimeout(Duration.ONE_MINUTE);

        asyncService.initialize();

        await().until(asyncService::isInitialized);
    }

    @Test
    void initializeTest2() {
        asyncService.initialize();
        await().atLeast(Duration.ONE_HUNDRED_MILLISECONDS) // > 100мс
                .atMost(Duration.FIVE_SECONDS) // < 5c
                .with()
                .pollInterval(Duration.ONE_HUNDRED_MILLISECONDS)
                .until(asyncService::isInitialized);
    }

    @Test
    void initializeTest3() {
        asyncService.initialize();

        await().until(asyncService::isInitialized);

        long value = 5;
        asyncService.addValue(value);
        await().until(asyncService::getValue, Matchers.equalTo(value));
    }

    @Test
    void initializeTest4() {
        asyncService.initialize();

        given().ignoreException(IllegalStateException.class)
                .await().atMost(Duration.FIVE_SECONDS)
                .atLeast(Duration.FIVE_HUNDRED_MILLISECONDS)
                .until(asyncService::getValue, Matchers.equalTo(0L));
    }

    @Test
    void initializeTest5() { // Устаревшая функция
        asyncService.initialize();
        await().untilCall(AwaitilityClassProxy.to(asyncService).isInitialized(), Matchers.equalTo(true));
    }

    @Test
    void initializeTest6() {
        asyncService.initialize();
        await().until(Awaitility.fieldIn(asyncService)
                .ofType(boolean.class)
                .andWithName("initialized"), Matchers.equalTo(true));
    }
}