package com.github.rusichpt.rabbitmq.producer.config;

import com.rabbitmq.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.scheduler.Schedulers;
import reactor.rabbitmq.*;

import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

@Configuration
public class RabbitConfig {

    @Bean
    ConnectionFactory connectionFactory(@Value("${rmq.uri}") String uri)
            throws URISyntaxException, NoSuchAlgorithmException, KeyManagementException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setUri(uri);
        connectionFactory.setAutomaticRecoveryEnabled(true);
        connectionFactory.useNio();
        connectionFactory.setConnectionTimeout(0);
        return connectionFactory;
    }

    @Bean
    public SenderOptions senderOptions(ConnectionFactory connectionFactory,
                                       @Value("${rmq.connection-name}") String connectionName) {
        return new SenderOptions()
                .connectionFactory(connectionFactory)
                .connectionSupplier(factory -> factory.newConnection(connectionName + "-sender"))
                .resourceManagementScheduler(Schedulers.boundedElastic());
    }

    @Bean
    public ReceiverOptions receiverOptions(ConnectionFactory connectionFactory,
                                           @Value("${rmq.connection-name}") String connectionName) {
        return new ReceiverOptions()
                .connectionFactory(connectionFactory)
                .connectionSupplier(factory -> factory.newConnection(connectionName + "-receiver"))
                .connectionSubscriptionScheduler(Schedulers.boundedElastic());
    }

    @Bean
    public Sender sender(SenderOptions senderOptions) {
        return RabbitFlux.createSender(senderOptions);
    }

    @Bean
    Receiver receiver(ReceiverOptions receiverOptions) {
        return RabbitFlux.createReceiver(receiverOptions);
    }
}
