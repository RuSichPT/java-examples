package com.github.rusichpt.camunda;

import io.camunda.zeebe.spring.client.properties.ZeebeClientConfigurationProperties;
import io.zeebe.containers.ZeebeContainer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.test.context.DynamicPropertyRegistry;

@TestConfiguration(proxyBeanMethods = false)
@Slf4j
public class TestCamundaApplication {

    public static void main(String[] args) {
        SpringApplication.from(CamundaApplication::main).with(TestCamundaApplication.class).run(args);
    }

    @Bean(initMethod = "start", destroyMethod = "stop")
    public ZeebeContainer zeebeContainer(DynamicPropertyRegistry properties) {
        ZeebeContainer zeebeContainer = new ZeebeContainer();
        properties.add("zeebe.client.broker.gateway-address", zeebeContainer::getExternalGatewayAddress);
        properties.add("zeebe.client.security.plaintext", () -> true);
        log.info("ZeebeContainer is created");
        return zeebeContainer;
    }

    @Bean
    @Primary
    @DependsOn("zeebeContainer")
    public ZeebeClientConfigurationProperties zeebeClientConfigurationProperties(Environment environment) {
        ZeebeClientConfigurationProperties properties = new ZeebeClientConfigurationProperties(environment);
        log.info("ZeebeClientConfigurationProperties is created");
        return properties;
    }

}
