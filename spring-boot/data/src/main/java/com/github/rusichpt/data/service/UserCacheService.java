package com.github.rusichpt.data.service;

import com.github.rusichpt.custom.starter.model.User;
import com.github.rusichpt.custom.starter.service.UserService;
import com.github.rusichpt.data.entity.UserEntity;
import com.github.rusichpt.data.repo.UserRepository;
import com.hazelcast.map.IMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserCacheService implements UserService {
    private final IMap<Long, UserEntity> userMap;
    private final UserRepository repo;
    private static final String CACHE_CLEARED = "Cache cleared";

    @Override
    public Optional<User> getUser(Long id) {
        UserEntity entity = userMap.computeIfAbsent(id, entityId -> repo.findById(entityId)
                .orElseThrow());
        log.info("UserCache: {}", entity);
        return Optional.of(new User(entity.getId(), entity.getName(), entity.getAge(), entity.getName()));
    }

    @Override
    public List<User> getUsers() {
        return repo.findAll().stream()
                .map(entity -> new User(entity.getId(), entity.getName(), entity.getAge(), entity.getName()))
                .toList();
    }

    public String clear() {
        userMap.clear();
        log.info(CACHE_CLEARED);
        return CACHE_CLEARED;
    }
}
