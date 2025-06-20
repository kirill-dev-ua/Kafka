package com.example.kafka.service;

import com.example.kafka.model.Order;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderService.class);
    private final OrderKafkaProducer orderKafkaProducer;

    public void saveOrder(Order orderToSave) {
        // передаем в kafka
        orderKafkaProducer.sendOrderToKafka(orderToSave);
        log.info("Order successfully saved: id={}", orderToSave.orderId());
    }
}
