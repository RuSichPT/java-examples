package com.github.rusichpt.logback.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class JsonLayoutController {
    @RequestMapping("/json1")
    public String index() {
        log.info("JSON message");

        return "Howdy! JsonLayout";
    }
}
