package com.github.rusichpt.dataspringbootstarter.controller;


import com.github.rusichpt.dataspringbootstarter.autoconfigure.DataProperties;
import com.github.rusichpt.dataspringbootstarter.dto.UserDTO;
import com.github.rusichpt.dataspringbootstarter.mapper.UserMapper;
import com.github.rusichpt.dataspringbootstarter.service.UserService;
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
    private final UserMapper mapper;

    @GetMapping
    public String hello() {
        return properties.getText();
    }

    @GetMapping(path = "/users")
    public List<UserDTO> getAllUsers() {
        return userService.getUsers().stream()
                .map(mapper::toUserDTO)
                .toList();
    }

    @GetMapping(path = "/users/{id}")
    public UserDTO getUser(@PathVariable Long id) {
        return mapper.toUserDTO(userService.getUser(id).orElse(null));
    }
}
