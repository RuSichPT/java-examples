package com.github.rusichpt.rabbitmq.consumer.command;

import lombok.Data;

@Data
public class MessageCommand implements Command {
    String text;
}
