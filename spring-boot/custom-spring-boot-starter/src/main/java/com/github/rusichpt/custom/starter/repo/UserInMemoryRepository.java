package com.github.rusichpt.custom.starter.repo;

import com.github.rusichpt.custom.starter.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserInMemoryRepository {
    private final List<User> users = new ArrayList<>();

    public UserInMemoryRepository() {
        users.add(new User(1L, "Павел", 27, "Java developer"));
        users.add(new User(2L, "'Александр'", 20, "C++ developer"));
        users.add(new User(3L, "'Дмитрий'", 18, "С# developer"));
        users.add(new User(4L, "'Oлег'", 25, "Python developer"));
    }

    public Optional<User> findById(Long id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

    public List<User> findAll() {
        return users;
    }

    public User save(User user) {
        users.add(user);
        return user;
    }
}
