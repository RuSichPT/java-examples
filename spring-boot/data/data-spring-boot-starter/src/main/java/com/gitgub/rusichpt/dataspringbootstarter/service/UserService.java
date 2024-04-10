package com.gitgub.rusichpt.dataspringbootstarter.service;


import com.gitgub.rusichpt.dataspringbootstarter.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> getUser(Long id);

    List<User> getUsers();
}
