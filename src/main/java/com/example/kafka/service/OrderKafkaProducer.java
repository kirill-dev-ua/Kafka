package com.example.kafka.service;

import com.example.kafka.model.Order;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderKafkaProducer {
    private static final Logger log = LoggerFactory.getLogger(OrderKafkaProducer.class);
    private final KafkaTemplate<String, Order> kafkaTemplate;

    public void sendOrderToKafka(Order order){
        kafkaTemplate.send("orders", order.orderId(), order);
        log.info("Order sent to kafka: id={}", order.orderId());
    }
}
