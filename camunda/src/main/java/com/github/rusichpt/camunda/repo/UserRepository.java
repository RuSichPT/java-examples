package com.github.rusichpt.camunda.repo;

import com.github.rusichpt.camunda.dto.User;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Component
public class UserRepository {
    HashMap<Long, User> users = new HashMap<>();

    @PostConstruct
    public void init() {
        users.put(1L, new User("Павел", 27, 1000.0));
        users.put(2L, new User("Андрей", 21, 1500.0));
    }

    public Optional<User> findById(Long id) {
        return Optional.ofNullable(users.get(id));
    }

    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }
}
