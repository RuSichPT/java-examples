package com.github.rusichpt.webflux.controller;

import com.github.rusichpt.webflux.model.Employee;
import com.github.rusichpt.webflux.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    @GetMapping("/{id}")
    public Mono<Employee> getEmployeeById(@PathVariable("id") String id) {
        return employeeRepository.findEmployeeById(id);
    }

    @GetMapping
    public Flux<Employee> getAllEmployees() {
        return employeeRepository.findAllEmployees();
    }

    @PostMapping("/update")
    public Mono<Employee> updateEmployee(@RequestBody Employee employee) {
        return employeeRepository.updateEmployee(employee);
    }
}
