package com.github.rusichpt.camunda.service;


import com.github.rusichpt.camunda.dto.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> getUser(Long id);

    List<User> getUsers();
}
