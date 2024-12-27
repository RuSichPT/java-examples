package com.github.rusichpt.data.controller;

import com.github.rusichpt.custom.starter.dto.UserDTO;
import com.github.rusichpt.custom.starter.mapper.UserMapper;
import com.github.rusichpt.custom.starter.service.UserService;
import com.github.rusichpt.data.service.UserCacheService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserCacheController {

    @Qualifier("userCacheService")
    private final UserService userService;
    private final UserMapper mapper;

    @GetMapping(path = "/cache/users/{id}")
    public UserDTO getCacheUser(@PathVariable Long id) {
        return mapper.toUserDTO(userService.getUser(id).orElse(null));
    }

    @GetMapping(path = "/cache/clear")
    public String clearCache() {
        if (userService instanceof UserCacheService userCacheService) {
            return userCacheService.clear();
        } else {
            throw new UnsupportedOperationException();
        }
    }

}
