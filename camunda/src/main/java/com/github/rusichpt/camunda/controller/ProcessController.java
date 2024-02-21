package com.github.rusichpt.camunda.controller;

import com.github.rusichpt.camunda.service.ProcessService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ProcessController {

    private final ProcessService processService;

    @GetMapping("/start/{id}")
    public String start(@PathVariable Long id, @RequestParam String corKey) {
        return processService.start(id, corKey);
    }

    @GetMapping("/message")
    public String sendMessage(@RequestParam String corKey) {
        return processService.sendMessage(corKey);
    }
}