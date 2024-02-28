package com.github.rusichpt.dockercompose.service;


import com.github.rusichpt.dockercompose.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> getUser(Long id);

    List<User> getUsers();
}
