package com.example.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderService.class);

    private final OrderKafkaProducer orderKafkaProducer;

    public OrderService(OrderKafkaProducer orderKafkaProducer) {
        this.orderKafkaProducer = orderKafkaProducer;
    }

    public void saveOrder(Order orderToSave) {

        orderKafkaProducer.sendOrderToKafka(orderToSave);
        log.info("Order successfully saved: id={}", orderToSave.orderId());
    }
}
