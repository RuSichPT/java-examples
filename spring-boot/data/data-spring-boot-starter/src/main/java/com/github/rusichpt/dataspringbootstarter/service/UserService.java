package com.github.rusichpt.dataspringbootstarter.service;


import com.github.rusichpt.dataspringbootstarter.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> getUser(Long id);

    List<User> getUsers();
}
