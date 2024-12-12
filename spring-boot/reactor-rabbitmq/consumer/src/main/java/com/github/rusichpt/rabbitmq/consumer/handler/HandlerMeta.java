package com.github.rusichpt.rabbitmq.consumer.handler;

import com.github.rusichpt.rabbitmq.consumer.command.Command;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HandlerMeta<I extends Command> {

    private String postfixRoutingKey;

    private Class<I> commandClass;
}
