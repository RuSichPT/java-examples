package com.github.rusichpt.custom.starter.service;


import com.github.rusichpt.custom.starter.entity.User;
import com.github.rusichpt.custom.starter.repo.UserInMemoryRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class UserInMemoryService implements UserService {

    private final UserInMemoryRepository repo;

    @Override
    public User saveUser(User user) {
        return repo.save(user);
    }

    @Override
    public Optional<User> getUser(Long id) {
        return repo.findById(id);
    }

    @Override
    public List<User> getUsers() {
        return repo.findAll();
    }
}
