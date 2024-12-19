package com.github.rusichpt.custom.starter;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@ComponentScan
@ConfigurationPropertiesScan
public class AutoConfiguration {

    @PostConstruct
    public void info() {
        log.info("Bean AutoConfiguration created");
    }
}
