package com.github.rusichpt.custom.starter;

import com.github.rusichpt.custom.starter.mapper.ProductMapper;
import com.github.rusichpt.custom.starter.mapper.ProductMapperImpl;
import com.github.rusichpt.custom.starter.mapper.UserMapper;
import com.github.rusichpt.custom.starter.mapper.UserMapperImpl;
import com.github.rusichpt.custom.starter.repo.ProductInMemoryRepository;
import com.github.rusichpt.custom.starter.repo.UserInMemoryRepository;
import com.github.rusichpt.custom.starter.service.ProductInMemoryService;
import com.github.rusichpt.custom.starter.service.ProductService;
import com.github.rusichpt.custom.starter.service.UserInMemoryService;
import com.github.rusichpt.custom.starter.service.UserService;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class AutoConfiguration {

    @PostConstruct
    public void info() {
        log.info("Bean AutoConfiguration created");
    }

    @Bean
    public ProductInMemoryRepository productInMemoryRepository() {
        return new ProductInMemoryRepository();
    }

    @Bean
    @ConditionalOnMissingBean(ProductService.class)
    public ProductService productService(ProductInMemoryRepository repository) {
        return new ProductInMemoryService(repository);
    }

    @Bean
    public UserInMemoryRepository userInMemoryRepository() {
        return new UserInMemoryRepository();
    }

    @Bean
    @ConditionalOnMissingBean(UserService.class)
    public UserService userService(UserInMemoryRepository repository) {
        return new UserInMemoryService(repository);
    }

    @Bean
    public ProductMapper productMapper() {
        return new ProductMapperImpl();
    }

    @Bean
    public UserMapper userMapper() {
        return new UserMapperImpl();
    }
}
