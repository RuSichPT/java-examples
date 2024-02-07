package com.github.rusichpt.camundaexample.service;

import com.github.rusichpt.camundaexample.dto.User;

import java.util.List;

public interface UserService {
    User getUser(Long id);
    List<User> getUsers();
}
