package com.github.rusichpt.rabbitmq.consumer.listener;

import com.github.rusichpt.rabbitmq.consumer.command.Command;
import com.github.rusichpt.rabbitmq.consumer.handler.CommandHandler;
import com.github.rusichpt.rabbitmq.consumer.handler.HandlerMeta;
import com.github.rusichpt.rabbitmq.consumer.helper.RabbitHelper;
import com.rabbitmq.client.Delivery;
import jakarta.annotation.PostConstruct;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ValidationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.rabbitmq.Receiver;

import java.util.List;
import java.util.Set;
import java.util.function.Function;

@Slf4j
@Component
@RequiredArgsConstructor
public class RabbitListenerInitializer {

    private final RabbitHelper helper;
    private final Receiver receiver;
    private final Validator validator;
    private final List<CommandHandler<? extends Command>> workers;

    @Value("${rmq.exchange}")
    private String exchange;
    @Value("${rmq.routingKey}")
    private String routingKey;

    @PostConstruct
    public void postConstruct() {
        initHandlers();
    }

    private void initHandlers() {
        workers.forEach(worker -> {
            String fullRoutingKey = "%s.%s".formatted(routingKey, worker.getHandlerMeta().getPostfixRoutingKey());
            String queue = "Command_" + fullRoutingKey;
            helper.declareExchangeAndQueue(exchange, fullRoutingKey, queue)
                    .block();

            receiver.consumeAutoAck(queue)
                    .flatMap(delivery -> Mono.just(delivery)
                            .doOnNext(RabbitHelper.log(queue))
                            .map(mapAndValidate(worker.getHandlerMeta()))
                            .flatMap(worker::execute)
                            .flatMap(result -> helper.sendMessage(exchange,
                                    routingKey, delivery.getProperties(), result))
                            .onErrorResume(Exception.class, e -> {
                                String message = e.getMessage();
                                log.error(message, e);
                                return Mono.empty();
                            }))
                    .subscribe();
        });
    }

    private Function<Delivery, Command> mapAndValidate(HandlerMeta<? extends Command> handlerMeta) {
        return msg -> {
            Command command = helper.parseBodyTo(handlerMeta.getCommandClass()).apply(msg);
            Set<ConstraintViolation<Command>> validateResult = validator.validate(command);
            validateResult.stream().findFirst().ifPresent(violation -> {
                String errorMessage = String.format(violation.getMessage(), violation.getPropertyPath());
                throw new ValidationException("ValidationException %s".formatted(errorMessage));
            });
            return command;
        };
    }
}
