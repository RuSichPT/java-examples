package com.github.rusichpt.camundaexample.service;

import io.camunda.zeebe.client.ZeebeClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProcessService {
    private final ZeebeClient zeebeClient;
    private final UserService userService;

    public String start(Long id) {

        Map<String, Object> variables = new HashMap<>();
        variables.put("id", id);

        zeebeClient.newCreateInstanceCommand()
                .bpmnProcessId("user-process")
                .latestVersion()
                .variables(variables)
                .send()
                .join();

        return "Process started. " + userService.getUser(id);
    }
}
