package com.github.rusichpt.data.controller;

import com.github.rusichpt.custom.starter.dto.UserDTO;
import com.github.rusichpt.custom.starter.mapper.UserMapper;
import com.github.rusichpt.custom.starter.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    @Qualifier("userServiceImpl")
    private final UserService userService;
    private final UserMapper mapper;

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
