package com.example.kafka.config;

import com.example.kafka.model.Order;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Bean
    public ProducerFactory<String, Order> producerFactory(ObjectMapper objectMapper){
        Map<String, Object> configProperties = new HashMap<>();
        configProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

        // запись order в kafka
        JsonSerializer<Order> serializer = new JsonSerializer<>(objectMapper);
        serializer.setAddTypeInfo(false); // тип модели не добавляем в json

        return new DefaultKafkaProducerFactory<>(
                configProperties, // подключение
                new StringSerializer(), // сериализация ключей
                serializer
        );
    }

    // отослать событие в kafka
    @Bean
    public KafkaTemplate<String, Order> kafkaTemplate(ProducerFactory<String, Order> producerFactory){
        return new KafkaTemplate<>(producerFactory);
    }
}
