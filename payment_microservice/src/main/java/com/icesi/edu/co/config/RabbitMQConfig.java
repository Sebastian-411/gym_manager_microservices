package com.icesi.edu.co.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Bean
    public Queue pagosQueue() {
        return QueueBuilder.durable("pagos-queue")
                .withArgument("x-dead-letter-exchange", "")
                .withArgument("x-dead-letter-routing-key", "pagos-dlq")
                .withArgument("x-message-ttl", 30000) // 30 segundos
                .build();
    }

    @Bean
    public Queue pagosDLQ() {
        return QueueBuilder.durable("pagos-dlq").build();
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("pagos-exchange");
    }

    @Bean
    public Binding bindingPagosQueue(Queue pagosQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(pagosQueue).to(directExchange).with("pagos-queue");
    }

    @Bean
    public Binding bindingPagosDLQ(Queue pagosDLQ, DirectExchange directExchange) {
        return BindingBuilder.bind(pagosDLQ).to(directExchange).with("pagos-dlq");
    }

}