package com.icesi.edu.co.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Bean
    public Queue change_scheduleQueue() {
        return new Queue("change_schedule.queue", true);
    }

    @Bean
    public TopicExchange change_scheduleExchange() {
        return new TopicExchange("classes.exchange");
    }

    @Bean   
    public Binding binding(Queue change_scheduleQueue, TopicExchange change_scheduleExchange) {
        return BindingBuilder.bind(change_scheduleQueue).to(change_scheduleExchange).with("classes.routingkey");
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
}