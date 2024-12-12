package com.github.rusichpt.rabbitmq.consumer.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.rusichpt.rabbitmq.consumer.command.Command;
import com.github.rusichpt.rabbitmq.consumer.command.MessageCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageCommandHandler implements CommandHandler<MessageCommand> {

    private final ObjectMapper mapper;

    @Override
    public Mono<JsonNode> execute(Command command) {
        return Mono.just(command)
                .map(e -> {
                    try {
                        return mapper.readTree(mapper.writeValueAsString(e));
                    } catch (JsonProcessingException ex) {
                        throw new RuntimeException(ex);
                    }
                });

    }

    @Override
    public HandlerMeta<MessageCommand> getHandlerMeta() {
        return new HandlerMeta<>("message", MessageCommand.class);
    }
}
