package com.gitgub.rusichpt.dataspringbootstarter.service;


import com.gitgub.rusichpt.dataspringbootstarter.entity.User;
import com.gitgub.rusichpt.dataspringbootstarter.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repo;

    @Override
    public Optional<User> getUser(Long id) {
        return repo.findById(id);
    }

    @Override
    public List<User> getUsers() {
        return repo.findAll();
    }
}
