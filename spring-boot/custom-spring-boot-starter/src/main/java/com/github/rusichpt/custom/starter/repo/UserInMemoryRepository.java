package com.github.rusichpt.custom.starter.repo;

import com.github.rusichpt.custom.starter.model.User;
import com.github.rusichpt.custom.starter.service.UserInMemortService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@ConditionalOnBean(UserInMemortService.class)
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
}
