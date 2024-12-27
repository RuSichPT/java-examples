package com.github.rusichpt.data.config;

import com.hazelcast.config.Config;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@ConfigurationProperties(prefix = "hazelcast")
@Primary
@Setter
@Getter
@Profile("datasource")
public class HazelcastConfigProperties extends Config {
}
