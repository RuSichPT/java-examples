package com.github.rusichpt.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeDAO dao;
    private static Integer id = 5;

    @GetMapping(path = "/count")
    public Integer countOfEmployees() {
        return dao.getCountOfEmployees();
    }

    @GetMapping(path = "/add")
    public Integer addEmployee() {
        return dao.addEmployee(++id);
    }

    @GetMapping(path = "/name/first/{id}")
    public String getFirstName(@PathVariable("id") Integer id) {
        return dao.getFirstNameUsingMapSqlParameterSource(id);
    }

    @GetMapping(path = "/count/{firstname}")
    public Integer getFirstName(@PathVariable("firstname") String firstName) {
        return dao.getCountUsingBeanPropertySqlParameterSource(firstName);
    }

//    @GetMapping(path = "/employees")
//    public List<Employee> getAll() {
//
//    }
}
