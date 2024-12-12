package com.github.rusichpt.rabbitmq.consumer.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Delivery;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.rabbitmq.OutboundMessage;
import reactor.rabbitmq.Sender;

import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

import static com.rabbitmq.client.BuiltinExchangeType.TOPIC;
import static reactor.rabbitmq.ExchangeSpecification.exchange;
import static reactor.rabbitmq.ResourcesSpecification.binding;
import static reactor.rabbitmq.ResourcesSpecification.queue;

@Slf4j
@Component
public class RabbitHelper {

    protected final ObjectMapper objectMapper;
    protected final Sender sender;

    public RabbitHelper(ObjectMapper objectMapper, Sender sender) {
        this.objectMapper = objectMapper;
        this.sender = sender;
    }

    public static final Map<String, Object> DEAD_LETTER_EXCHANGE = Map.of("x-dead-letter-exchange", "dead-letter-exchange");

    public static Consumer<Delivery> log(String queue) {
        return (Delivery delivery) -> {
            String data = new String(delivery.getBody(), StandardCharsets.UTF_8);
            log.info(">> {}: {}", queue, data);
        };
    }

    public <T> Function<Delivery, T> parseBodyTo(Class<T> tClass) {
        return parseBodyTo(new TypeReference<T>() {
            @Override
            public Type getType() {
                return tClass;
            }
        });
    }

    public <T> Function<Delivery, T> parseBodyTo(TypeReference<T> valueTypeRef) {
        return delivery -> {
            try {
                return objectMapper.readValue(delivery.getBody(), valueTypeRef);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    public Mono<Void> declareExchangeAndQueue(@NonNull String exchange, @NonNull String routingKey, @NonNull String queue) {
        return sender.declareExchange(exchange(exchange).durable(true))
                .then(sender.declareQueue(queue(queue).durable(true).arguments(DEAD_LETTER_EXCHANGE)))
                .then(sender.bind(binding(exchange, routingKey, queue)))
                .then();
    }

    public Mono<Void> declareTopicAndQueue(@NonNull String exchange, @NonNull String routingKey, @NonNull String queue) {
        return sender.declareExchange(exchange(exchange).durable(true).type(TOPIC.getType()))
                .then(sender.declareQueue(queue(queue).durable(true).arguments(DEAD_LETTER_EXCHANGE)))
                .then(sender.bind(binding(exchange, routingKey, queue)))
                .then();
    }

    public Mono<Void> sendMessage(@NonNull String exchange, @NonNull String routingKey, AMQP.BasicProperties properties, Object value) {
        byte[] bytes;
        try {
            bytes = objectMapper.writeValueAsBytes(value);
        } catch (JsonProcessingException e) {
            return Mono.error(new RuntimeException(e));
        }

        return sendMessage(exchange, routingKey, properties, bytes);
    }

    public Mono<Void> sendMessage(@NonNull String exchange, @NonNull String routingKey, AMQP.BasicProperties properties, String body) {
        return sendMessage(exchange, routingKey, properties, body.getBytes(StandardCharsets.UTF_8));
    }

    public Mono<Void> sendMessage(@NonNull String exchange, @NonNull String routingKey, AMQP.BasicProperties properties, byte[] body) {
        return sender.send(Mono.just(new OutboundMessage(exchange, routingKey, properties, body)));
    }
}
