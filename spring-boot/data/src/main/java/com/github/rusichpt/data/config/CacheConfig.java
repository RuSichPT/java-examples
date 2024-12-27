package com.github.rusichpt.data.config;

import com.github.rusichpt.data.entity.UserEntity;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class CacheConfig {

    private final HazelcastInstance hazelcastInstance;

    @Bean
    public IMap<Long, UserEntity> userMap() {
        return hazelcastInstance.getMap("userMap");
    }
}
