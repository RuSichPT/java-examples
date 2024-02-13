package com.github.rusichpt.data.service;


import com.github.rusichpt.data.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> getUser(Long id);

    List<User> getUsers();
}
