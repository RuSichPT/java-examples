package com.github.rusichpt.configproperties;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@Slf4j
@ConfigurationPropertiesScan
public class ConfigPropertiesApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(ConfigPropertiesApplication.class, args);
        ConfigProperties properties = run.getBean(ConfigProperties.class);
        log.info(properties.toString());
    }

}
