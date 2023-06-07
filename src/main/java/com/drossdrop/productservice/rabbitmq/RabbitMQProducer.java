package com.drossdrop.productservice.rabbitmq;

import com.drossdrop.productservice.dto.ProductRabbitMQ;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQProducer.class);

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    public void sendMessage(String message) {
        LOGGER.info("Sending message...");
        rabbitTemplate.convertAndSend("product_exchange", "product_routing_key", message);
    }

    public void sendProduct(ProductRabbitMQ product) {
        LOGGER.info(String.format("Sending message...-> %s", product.toString()));
//        ObjectMapper objectMapper = new ObjectMapper();
//        String json = null;
//        try {
//            json = objectMapper.writeValueAsString(product);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
        rabbitTemplate.convertAndSend("product_json", "product_routing_json_key", product);
    }
}
