package com.github.rusichpt.camundaexample.service;

import com.github.rusichpt.camundaexample.dto.User;
import com.github.rusichpt.camundaexample.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repo;

    @Override
    public User getUser(Long id) {
        return repo.getUser(id);
    }

    @Override
    public List<User> getUsers() {
        return repo.getUsers();
    }
}
