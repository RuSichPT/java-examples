package com.github.rusichpt.camundaexample.controller;

import com.github.rusichpt.camundaexample.dto.User;
import com.github.rusichpt.camundaexample.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping()
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public String hello() {
        return "Hello World";
    }

    @GetMapping(path = "/users")
    public List<User> getAll() {
        return userService.getUsers();
    }

    @GetMapping(path = "/users/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }
}
