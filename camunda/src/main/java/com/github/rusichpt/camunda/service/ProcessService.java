package com.github.rusichpt.camunda.service;

import io.camunda.zeebe.client.ZeebeClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProcessService {
    private final ZeebeClient zeebeClient;
    private final UserService userService;

    public String start(Long id, String corKey) {
        Map<String, Object> variables = Map.of("id", id, "corKey", corKey);

        zeebeClient.newCreateInstanceCommand()
                .bpmnProcessId("user-process")
                .latestVersion()
                .variables(variables)
                .send()
                .join();

        String result = "Process started. " + userService.getUser(id).orElseThrow();
        log.info(result);

        return result;
    }

    public String sendMessage(String corKey) {
        zeebeClient.newPublishMessageCommand()
                .messageName("messageSalary")
                .correlationKey(corKey)
                .send()
                .join();

        String result = "Message sent, corrKey: " + corKey;
        log.info(result);

        return result;
    }
}
