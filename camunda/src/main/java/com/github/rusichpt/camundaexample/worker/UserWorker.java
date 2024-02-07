package com.github.rusichpt.camundaexample.worker;

import com.github.rusichpt.camundaexample.dto.User;
import com.github.rusichpt.camundaexample.service.UserService;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import io.camunda.zeebe.spring.client.annotation.Variable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
@RequiredArgsConstructor
public class UserWorker {
    private final UserService userService;

    @JobWorker(type = "user.log")
    public void logUser(@Variable User user) {
        log.info(user.toString());
    }

    @JobWorker(type = "user.calculateSalary")
    public Map<String, Double> calculateSalary(@Variable User user) {
        log.info(String.format("%s has salary %s", user.getName(), user.getSalary()));
        return Map.of("salary", user.getSalary());
    }

    @JobWorker(type = "user.find")
    public Map<String, User> findUser(@Variable Long id) {
        User user = userService.getUser(id);
        log.info("Find user: {}", user);
        Map<String, User> vars = new HashMap<>();
        vars.put("user", user);
        return vars;
    }
}
