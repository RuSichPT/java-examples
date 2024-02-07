package com.github.rusichpt.camundaexample.repo;

import com.github.rusichpt.camundaexample.dto.User;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class UserRepository {
    HashMap<Long, User> users = new HashMap<>();

    @PostConstruct
    public void init() {
        users.put(1L, new User("Павел", 27, 1000.0));
        users.put(2L, new User("Андрей", 21, 1500.0));
    }

    public User getUser(Long id) {
        return users.get(id);
    }

    public List<User> getUsers() {
        return new ArrayList<>(users.values());
    }
}
