package com.github.rusichpt.data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;

@TestConfiguration(proxyBeanMethods = false)
public class TestDataApplication {

    public static void main(String[] args) {
        SpringApplication.from(DataApplication::main).with(TestDataApplication.class).run(args);
    }

    @Bean(initMethod = "start", destroyMethod = "stop")
    @ServiceConnection
    public PostgreSQLContainer<?> postgreSQLContainer() {
        return new PostgreSQLContainer<>("postgres:14.1");
    }

    // Без     @ServiceConnection
//    @Bean
//    public DataSource dataSource(PostgreSQLContainer<?> postgreSQLContainer) {
//        HikariDataSource hikariDataSource = new HikariDataSource();
//        hikariDataSource.setJdbcUrl(postgreSQLContainer.getJdbcUrl());
//        hikariDataSource.setUsername(postgreSQLContainer.getUsername());
//        hikariDataSource.setPassword(postgreSQLContainer.getPassword());
//
//        return hikariDataSource;
//    }

}
