package com.gitgub.rusichpt.dataspringbootstarter.controller;


import com.gitgub.rusichpt.dataspringbootstarter.autoconfigure.DataProperties;
import com.gitgub.rusichpt.dataspringbootstarter.entity.User;
import com.gitgub.rusichpt.dataspringbootstarter.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final DataProperties properties;

    @GetMapping
    public String hello() {
        return properties.getText();
    }

    @GetMapping(path = "/users")
    public List<User> getAllUsers() {
        return userService.getUsers();
    }

    @GetMapping(path = "/users/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUser(id).orElse(null);
    }
}
