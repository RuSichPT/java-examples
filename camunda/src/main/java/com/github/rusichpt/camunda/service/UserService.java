package com.github.rusichpt.camunda.service;

import com.github.rusichpt.camunda.dto.User;

import java.util.List;

public interface UserService {
    User getUser(Long id);
    List<User> getUsers();
}
