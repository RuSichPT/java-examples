package com.github.rusichpt.data.repo;

import com.github.rusichpt.data.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.from;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class UserRepositoryTest {
    @Autowired
    UserRepository repo;

    @Container
    static final PostgreSQLContainer<?> DATABASE = new PostgreSQLContainer<>("postgres:14.1")
            .withDatabaseName("cross_sbp")
            .withUsername("sa")
            .withPassword("sa");

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", DATABASE::getJdbcUrl);
        registry.add("spring.datasource.username", DATABASE::getUsername);
        registry.add("spring.datasource.password", DATABASE::getPassword);
    }

    @Test
    @DisplayName("Должен корректно создать сущность")
    void test1() {
        User user = User.builder()
                .name("Вася")
                .age(26)
                .about("QA")
                .build();

        repo.save(user);

        assertThat(repo.findById(user.getId()))
                .isPresent()
                .contains(user);
    }

    @Test
    @DisplayName("Должен корректно получить сущность")
    void test2() {
        User expected = new User(1L, "Павел", 27, "Java developer");

        Optional<User> opt = repo.findById(1L);

        assertThat(opt)
                .isPresent()
                .contains(expected);
    }

    @Test
    @DisplayName("Должен корректно обновить сущность")
    void test3() {
        User user = repo.findById(1L).orElseThrow();
        user.setName("Антон");

        repo.save(user);

        assertThat(repo.findById(user.getId()).orElseThrow())
                .returns("Антон", from(User::getName));
    }

    @Test
    @DisplayName("Должен корректно обновить сущность")
    void test4() {
        User user = repo.findById(1L).orElseThrow();

        repo.delete(user);

        assertThat(repo.findById(user.getId()))
                .isEmpty();
    }
}