package com.productservice.util;

public interface Constants {

    String UPDATE_ORDER_TOPIC = "updateOrder";
    String NEW_ORDER_TOPIC = "newOrder";
    String BOOTSTRAP_SERVERS_CONFIG_VALUE = "kafka:9092";
    String CONSUMER_GROUP_ID = "order_consumer";
    String KAFKA_LISTENER_FACTORY = "orderMessageKafkaListenerFactory";
}
