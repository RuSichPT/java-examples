package com.github.rusichpt.camunda;

import com.github.rusichpt.camunda.common.ResultCaptor;
import com.github.rusichpt.camunda.dto.User;
import com.github.rusichpt.camunda.repo.UserRepository;
import com.github.rusichpt.camunda.worker.UserWorker;
import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.spring.client.annotation.value.ZeebeWorkerValue;
import io.camunda.zeebe.spring.client.bean.MethodInfo;
import io.camunda.zeebe.spring.client.jobhandling.JobWorkerManager;
import io.zeebe.containers.ZeebeContainer;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.org.awaitility.Awaitility;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import static com.github.rusichpt.camunda.CamundaApplicationTest.Configuration;
import static com.github.rusichpt.camunda.common.ZeebeClientUtils.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = {CamundaApplication.class, Configuration.class}
)
@Testcontainers
@Slf4j
@AutoConfigureMockMvc
class CamundaApplicationTest {
    @Autowired
    MockMvc mvc;
    @Autowired
    ZeebeClient client;
    @Autowired
    JobWorkerManager workerManager;
    @Autowired
    UserRepository repo;
    @Autowired
    EasyRandom random;

    @SpyBean
    UserWorker worker;
    @Autowired
    FindUserWorkerInterceptor findWorker;
    @Autowired
    CalculateSalaryUserWorkerInterceptor salaryWorker;
    @Autowired
    LogUserWorkerInterceptor logWorker;

    @Container
    static final ZeebeContainer ZEEBE_CONTAINER = new ZeebeContainer();

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
//        registry.add("zeebe.client.broker.gateway-address", () -> "localhost:26500");
        registry.add("zeebe.client.broker.gateway-address", ZEEBE_CONTAINER::getExternalGatewayAddress);
    }

    @AfterAll
    static void closeOpenWorkers(@Autowired JobWorkerManager workerManager) {
        workerManager.closeAllOpenWorkers();
    }

    @Test
    @DisplayName("UserWorker (все) должны отработать корректно через Аспекты")
    void test1() {
        Long id = 1L;
        UUID corKey = UUID.randomUUID();
        Map<String, Object> vars = Map.of("id", id, "corKey", corKey);
        User expected = repo.findById(id).orElseThrow();

        // Создать экземпляр процесса, начиная с шага
        createProcess(client, "user-process", HAS_USER, vars);

        Optional<ZeebeWorkerValue> opt1 = workerManager.findJobWorkerConfigByType("user.log");
        Optional<ZeebeWorkerValue> opt2 = workerManager.findJobWorkerConfigByType("user.find");
        Optional<ZeebeWorkerValue> opt3 = workerManager.findJobWorkerConfigByType("user.calculateSalary");

        assertThat(opt1).isNotEmpty();
        assertThat(opt2).isNotEmpty();
        assertThat(opt3).isNotEmpty();

        createMessage(client, "messageSalary", corKey.toString(), Collections.emptyMap());

        // ожидаем завершения работы worker'ов
        Awaitility.await()
                .atMost(3, TimeUnit.SECONDS)
                .until(() -> findWorker.isDone() && logWorker.isDone() && salaryWorker.isDone());

        assertThat(findWorker.getResult())
                .isNotEmpty()
                .hasValue(Map.of("user", expected));
        assertThat(salaryWorker.getResult())
                .isNotEmpty()
                .hasValue(Map.of("salary", expected.getSalary()));
    }

    @Test
    @DisplayName("UserWorker (find) должен отработать корректно, если не найден пользователь")
    void test2() {
        Long id = random.nextLong();
        UUID corKey = UUID.randomUUID();
        Map<String, Object> vars = Map.of("id", id, "corKey", corKey);

        // Создать экземпляр процесса, начиная с шага
        createProcess(client, "user-process", HAS_USER, vars);

        // ожидаем завершения работы worker'ов
        Awaitility.await()
                .atMost(3, TimeUnit.SECONDS)
                .until(() -> findWorker.isDone());

        assertThat(findWorker.getResult())
                .isNotEmpty();
        assertThat(findWorker.getResult().get().get("user"))
                .isNull();
    }

    @Test
    @SneakyThrows
    @DisplayName("UserWorker (все) должны отработать корректно через SpyBean")
    void test3() {
        Long id = 1L;
        UUID corKey = UUID.randomUUID();
        Map<String, Object> vars = Map.of("id", id, "corKey", corKey);
        User expected = repo.findById(id).orElseThrow();

        // Создаем ResultCaptor для каждого worker'a
        ResultCaptor<Map<String, User>> resultFind = new ResultCaptor<>();
        ResultCaptor<Map<String, Double>> resultCalcSalary = new ResultCaptor<>();
        ResultCaptor<Void> resultLog = new ResultCaptor<>();
        doAnswer(resultCalcSalary).when(worker).calculateSalary(any(User.class));
        doAnswer(resultFind).when(worker).findUser(any(Long.class));
        doAnswer(resultLog).when(worker).logUser(any(User.class));

        // Создать экземпляр процесса, начиная с шага
        createProcess(client, "user-process", HAS_USER, vars);

        // Вызываем API для генерации эвента
        mvc.perform(get("/message")
                        .param("corKey", corKey.toString()))
                .andExpect(status().isOk());

        // Ожидаем завершения работы worker'ов
        Awaitility.await()
                .atMost(3, TimeUnit.SECONDS)
                .until(() -> resultFind.isDone() && resultCalcSalary.isDone() && resultLog.isDone());

        assertThat(resultFind.getResult())
                .isNotEmpty()
                .isEqualTo(Map.of("user", expected));
        assertThat(resultCalcSalary.getResult())
                .isNotEmpty()
                .isEqualTo(Map.of("salary", expected.getSalary()));

        Mockito.verify(worker).calculateSalary(any(User.class));
        Mockito.verify(worker).logUser(any(User.class));
        Mockito.verify(worker).findUser(id);
    }

    /**
     * Для spring boot 2.x.x
     * <p>
     * Аспект используется для проверки результата работы {@link com.github.rusichpt.camunda.worker.UserWorker},
     * который инициализируется декларативно {@link io.camunda.zeebe.spring.client.annotation.JobWorker}
     * </p>
     * <p>
     * К сожалению, нет возможности использовать {@link SpyBean}, т.к.{@link MethodInfo}
     * полагается на спринговый {LocalVariableTableParameterNameDiscoverer}, который некорректно работает в случае
     * со @Spy proxy
     * </p>
     * <p>
     * Второй вариант, - переопределить {@link JobWorkerManager}, - является намного менее изящным решением, чем AOP
     * в силу особенности своей реализации
     * </p>
     */
    @Aspect
    static class FindUserWorkerInterceptor {
        private final AtomicReference<Map<String, User>> result = new AtomicReference<>();

        public Optional<Map<String, User>> getResult() {
            return Optional.ofNullable(result.get());
        }

        public boolean isDone() {
            return Optional.ofNullable(result.get()).isPresent();
        }

        @AfterReturning(value = "execution(* com.github.rusichpt.camunda.worker.UserWorker.findUser(..))",
                returning = "result")
        public void afterReturningCreateCheckResponse(JoinPoint jp, Object result) {
            @SuppressWarnings("unchecked") Map<String, User> responseMap = (Map<String, User>) result;
            this.result.set(responseMap);
        }
    }

    @Aspect
    static class CalculateSalaryUserWorkerInterceptor {
        private final AtomicReference<Map<String, Double>> result = new AtomicReference<>();

        public Optional<Map<String, Double>> getResult() {
            return Optional.ofNullable(result.get());
        }

        public boolean isDone() {
            return Optional.ofNullable(result.get()).isPresent();
        }

        @AfterReturning(value = "execution(* com.github.rusichpt.camunda.worker.UserWorker.calculateSalary(..))",
                returning = "result")
        public void afterReturningCreateCheckResponse(JoinPoint jp, Object result) {
            @SuppressWarnings("unchecked") Map<String, Double> responseMap = (Map<String, Double>) result;
            this.result.set(responseMap);
        }
    }

    @Aspect
    static class LogUserWorkerInterceptor {
        private final AtomicBoolean result = new AtomicBoolean(false);

        public boolean isDone() {
            return result.get();
        }

        @AfterReturning(value = "execution(* com.github.rusichpt.camunda.worker.UserWorker.logUser(..))",
                returning = "result")
        public void afterReturningCreateCheckResponse(JoinPoint jp, Object result) {
            this.result.set(true);
        }
    }

    static class Configuration {
        @Bean
        public FindUserWorkerInterceptor findUserWorkerInterceptor() {
            return new FindUserWorkerInterceptor();
        }

        @Bean
        public CalculateSalaryUserWorkerInterceptor calculateSalaryUserWorkerInterceptor() {
            return new CalculateSalaryUserWorkerInterceptor();
        }

        @Bean
        public LogUserWorkerInterceptor logUserWorkerInterceptor() {
            return new LogUserWorkerInterceptor();
        }

        @Bean
        public EasyRandom easyRandom() {
            return new EasyRandom();
        }
    }


}
