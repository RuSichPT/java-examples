package com.github.rusichpt.logback.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class LogstashController {
    @RequestMapping("/json3")
    public String index() {
        log.info("JSON message");

        return "Howdy! Logstash";
    }
}
