package com.github.rusichpt.webflux.client;

import com.github.rusichpt.webflux.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
public class EmployeeWebClient {

    WebClient client = WebClient.create("http://localhost:8080");

    public void consume() {

        Mono<Employee> employeeMono = client.get()
                .uri("/employees/{id}", "1")
                .retrieve()
                .bodyToMono(Employee.class);

        employeeMono.subscribe(employee -> log.info("Employee: {}", employee));

        Flux<Employee> employeeFlux = client.get()
                .uri("/employees")
                .retrieve()
                .bodyToFlux(Employee.class);

        employeeFlux.subscribe(employee -> log.info("Employee: {}", employee));
    }
}
