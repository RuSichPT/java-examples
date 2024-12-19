package com.github.rusichpt.custom.starter.service;

import com.github.rusichpt.custom.starter.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> getUser(Long id);

    List<User> getUsers();
}
