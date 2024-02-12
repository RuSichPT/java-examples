package com.github.rusichpt.camunda;

import io.camunda.zeebe.spring.client.annotation.Deployment;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Deployment(resources = "classpath:bpmn/user.bpmn")
public class CamundaExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(CamundaExampleApplication.class, args);
	}

}
