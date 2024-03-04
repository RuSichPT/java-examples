package com.github.rusichpt.multipledatabases;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.github.rusichpt")
@EnableJpaRepositories(basePackages = "com.github.rusichpt.data")
@EntityScan(basePackages = "com.github.rusichpt.data")
public class MultipleDatabasesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultipleDatabasesApplication.class, args);
	}

}
