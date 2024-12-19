package com.github.rusichpt.custom.starter.service;


import com.github.rusichpt.custom.starter.model.User;
import com.github.rusichpt.custom.starter.repo.UserInMemoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@ConditionalOnMissingBean(ProductService.class)
public class UserInMemortService implements UserService {

    private final UserInMemoryRepository repo;

    @Override
    public Optional<User> getUser(Long id) {
        return repo.findById(id);
    }

    @Override
    public List<User> getUsers() {
        return repo.findAll();
    }
}
