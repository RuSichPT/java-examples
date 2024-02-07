package com.github.rusichpt.camundaexample.controller;

import com.github.rusichpt.camundaexample.service.ProcessService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ProcessController {

    private final ProcessService processService;

    @GetMapping("/start/{id}")
    public String start(@PathVariable Long id) {
        return processService.start(id);
    }
}