package com.github.rusichpt.rabbitmq.producer.service;

import com.github.rusichpt.rabbitmq.producer.dto.Message;
import com.github.rusichpt.rabbitmq.producer.helper.RabbitHelper;
import com.rabbitmq.client.AMQP;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final RabbitHelper helper;

    @Value("${rmq.exchange}")
    private String exchange;
    @Value("${rmq.routingKey}")
    private String routingKey;
    @Value("${rmq.queue}")
    private String queue;

    public void sendMessage(Message message) {
        helper.declareExchangeAndQueue(exchange, routingKey, queue)
                .block();
        helper.sendMessage(exchange, routingKey, createProps(exchange), message)
                .subscribe();
    }

    private AMQP.BasicProperties createProps(String targetExchange) {
        return new AMQP.BasicProperties.Builder()
                .contentType(targetExchange)
                .deliveryMode(2) // 2 - Persistent, 1 - Transient
                .priority(1)
                .correlationId(UUID.randomUUID().toString())
                .build();
    }
}
