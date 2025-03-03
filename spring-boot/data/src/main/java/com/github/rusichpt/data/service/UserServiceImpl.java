package com.github.rusichpt.data.service;

import com.github.rusichpt.custom.starter.entity.User;
import com.github.rusichpt.custom.starter.service.UserService;
import com.github.rusichpt.data.entity.UserEntity;
import com.github.rusichpt.data.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository repo;

    @Override
    public User saveUser(User user) {
        UserEntity entity = repo.save(new UserEntity(null, user.getName(), user.getAge(), user.getName()));
        return new User(entity.getId(), entity.getName(), entity.getAge(), entity.getAbout());
    }

    @Override
    public Optional<User> getUser(Long id) {
        return repo.findById(id)
                .map(entity -> {
                    log.info("User: {}", entity);
                    return new User(entity.getId(), entity.getName(), entity.getAge(), entity.getAbout());
                });
    }

    @Override
    public List<User> getUsers() {
        return repo.findAll().stream()
                .map(entity -> new User(entity.getId(), entity.getName(), entity.getAge(), entity.getAbout()))
                .toList();
    }
}
