package com.github.rusichpt.data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestDataApplication {

    public static void main(String[] args) {
        SpringApplication.from(DataApplication::main).with(TestDataApplication.class).run(args);
    }

}
