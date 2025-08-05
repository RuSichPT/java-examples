package com.github.rusichpt.grafana.loki.tempo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LogbackController {

    @GetMapping("/log")
    public String index() {
        log.trace("A TRACE Message");
        log.debug("A DEBUG Message");
        log.info("An INFO Message");
        log.warn("A WARN Message");
        log.error("An ERROR Message");

        return "Howdy! Check out the Logs to see the output...";
    }

    @GetMapping("/log/{count}")
    public Integer index(@PathVariable Integer count) {

        for (int i = 0; i < count; i++) {
            log.info("spam");
        }

        return count;
    }
}
