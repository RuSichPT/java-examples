package com.github.rusichpt.rabbitmq.producer.controller;

import com.github.rusichpt.rabbitmq.producer.dto.Message;
import com.github.rusichpt.rabbitmq.producer.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MessageController {

    private final MessageService messageService;

    @PostMapping(path = "/send")
    public Message sendMessage(@RequestBody Message message) {
        messageService.sendMessage(message);
        log.info("send message {}", message);
        return message;
    }

}
