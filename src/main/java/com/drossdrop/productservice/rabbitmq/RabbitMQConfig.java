package com.drossdrop.productservice.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class RabbitMQConfig {

    @Bean
    public Queue queue() {
        return new Queue("myQueue", false);
    }
}