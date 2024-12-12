package com.github.rusichpt.rabbitmq.consumer.handler;

import com.fasterxml.jackson.databind.JsonNode;

import com.github.rusichpt.rabbitmq.consumer.command.Command;
import reactor.core.publisher.Mono;

public interface CommandHandler<I extends Command> {

    Mono<JsonNode> execute(Command command);

    HandlerMeta<I> getHandlerMeta();
}
