package com.humanresource.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author umeshkhatiwada13@infodev
 * @project human-resource
 * @created 29/08/2022 - 06:01
 */

@Configuration
public class Config {
    private static final String QUEUE_NAME = "mail.queue";
    private static final String EXCHANGE_NAME = "common.exchange";
    private static final String ROUTING_KEY = "mail.queue.key";

    @Bean
    public Queue mailQueue() {
        return new Queue(QUEUE_NAME, true);
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder
                .bind(mailQueue())
                .to(exchange())
                .with(ROUTING_KEY);
    }
}