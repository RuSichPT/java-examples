package com.github.rusichpt.mockito;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    public static void main(String[] args) {
        UserService service = new UserService(new UserRepository());

        User user = service.findUser(1L);

        log.info("User: {}", user);
    }
}
