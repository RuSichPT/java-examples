package com.github.rusichpt.nimbus.controller;


import com.fasterxml.jackson.core.type.TypeReference;
import com.github.rusichpt.nimbus.common.JwtFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {
    private final JwtFacade jwtFacade;

    @PostMapping("/sign")
    public String sign(@RequestBody Map<String, Object> claims) {
        return jwtFacade.sign(claims);
    }

    @PostMapping("/verify")
    public Map<String, Object> verifyAndGetClaims(@RequestBody String jwtCompact) {
        return jwtFacade.verifyAndGetClaims(jwtCompact, new TypeReference<>() {
        });
    }
}
