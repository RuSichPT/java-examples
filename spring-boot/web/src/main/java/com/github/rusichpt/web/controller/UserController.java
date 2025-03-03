package com.github.rusichpt.web.controller;


import com.github.rusichpt.custom.starter.dto.UserDTO;
import com.github.rusichpt.custom.starter.entity.User;
import com.github.rusichpt.custom.starter.mapper.UserMapper;
import com.github.rusichpt.custom.starter.service.UserService;
import com.github.rusichpt.web.dto.UserDTOWithPassword;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class UserController {

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

    @PostMapping(path = "/user")
    public UserDTO updateUser(@Valid @RequestBody UserDTOWithPassword userDTOWithPassword) {
        User user = mapper.toUser(userDTOWithPassword.getUserDTO());
        return mapper.toUserDTO(userService.saveUser(user));
    }
}
