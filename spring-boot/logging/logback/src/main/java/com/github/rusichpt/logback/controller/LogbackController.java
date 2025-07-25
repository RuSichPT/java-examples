package com.github.rusichpt.logback.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class LogbackController {

    @RequestMapping("/log")
    public String index() {
        log.trace("A TRACE Message");
        log.debug("A DEBUG Message");
        log.info("An INFO Message");
        log.warn("A WARN Message");
        log.error("An ERROR Message");

        return "Howdy! Check out the Logs to see the output...";
    }

    @RequestMapping("/log/{count}")
    public Integer index(@PathVariable Integer count) {

        for (int i = 0; i < count; i++) {
            log.info("spam");
        }

        return count;
    }
}
