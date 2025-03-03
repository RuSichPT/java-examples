package com.github.rusichpt.data.service;

import com.github.rusichpt.custom.starter.entity.User;
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
    private final IMap<Long, UserEntity> userCache;
    private final UserRepository repo;
    private static final String CACHE_CLEARED = "Cache cleared";

    @Override
    public User saveUser(User user) {
        UserEntity entity = repo.save(new UserEntity(null, user.getName(), user.getAge(), user.getName()));
        try {
            if (userCache.tryLock(entity.getId())) {
                userCache.put(entity.getId(), entity);
            } else {
                throw new RuntimeException("Не удалось получить блокировку на операцию");
            }
            return new User(entity.getId(), entity.getName(), entity.getAge(), entity.getAbout());
        } finally {
            userCache.unlock(user.getId());
        }
    }

    @Override
    public Optional<User> getUser(Long id) {
        UserEntity entity = userCache.computeIfAbsent(id, entityId -> repo.findById(entityId)
                .orElseThrow());
        log.info("UserCache: {}", entity);
        return Optional.of(new User(entity.getId(), entity.getName(), entity.getAge(), entity.getAbout()));
    }

    @Override
    public List<User> getUsers() {
        return repo.findAll().stream()
                .map(entity -> new User(entity.getId(), entity.getName(), entity.getAge(), entity.getAbout()))
                .toList();
    }

    public String clear() {
        userCache.clear();
        log.info(CACHE_CLEARED);
        return CACHE_CLEARED;
    }
}
