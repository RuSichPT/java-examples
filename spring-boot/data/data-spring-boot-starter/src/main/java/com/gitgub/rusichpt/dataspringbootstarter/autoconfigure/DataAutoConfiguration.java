package com.gitgub.rusichpt.dataspringbootstarter.autoconfigure;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ConfigurationPropertiesScan
@Slf4j
@ComponentScan(basePackages = "com.gitgub.rusichpt.dataspringbootstarter")
@EnableJpaRepositories(basePackages = "com.gitgub.rusichpt.dataspringbootstarter")
@EntityScan(basePackages = "com.gitgub.rusichpt.dataspringbootstarter")
public class DataAutoConfiguration {

    @PostConstruct
    public void info() {
        log.info("Bean DataAutoConfiguration created");
    }

}
