package com.github.rusichpt.mockito;


import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
public class UserRepository {
    private final List<User> users = new ArrayList<>();

    public UserRepository() {
        users.add(new User(1L, "Павел", 27, "Java developer"));
        users.add(new User(2L, "Александр", 20, "C++ developer"));
        users.add(new User(3L, "Дмитрий", 18, "С# developer"));
        users.add(new User(4L, "Oлег", 25, "Python developer"));
    }

    public Optional<User> findById(Long id) {
        log.info("Finding user by id {}", id);
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

    public Optional<User> findByName(String s) {
        return users.stream()
                .filter(user -> user.getName().equals(s))
                .findFirst();
    }

    public void delete(Long id) {
        users.removeIf(user -> user.getId().equals(id));
    }
}
