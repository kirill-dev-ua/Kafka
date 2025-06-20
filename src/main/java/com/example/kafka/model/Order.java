package com.example.kafka.model;

public record Order(String orderId, String product, Integer price, Integer quantity) {

}
