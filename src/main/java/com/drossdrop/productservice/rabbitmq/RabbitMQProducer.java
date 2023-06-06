package com.drossdrop.productservice.rabbitmq;

import com.drossdrop.productservice.dto.ProductRabbitMQ;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQProducer {
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    private Queue myQueue;

    @Autowired
    public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

//    public void sendMessage(String queueName, String message) {
//        rabbitTemplate.convertAndSend(queueName, message);
//    }

    public void sendProduct(ProductRabbitMQ product) {
//        ObjectMapper objectMapper = new ObjectMapper();
//        String json = null;
//        try {
//            json = objectMapper.writeValueAsString(product);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
        rabbitTemplate.convertAndSend(myQueue.getName(), product);
    }
}
