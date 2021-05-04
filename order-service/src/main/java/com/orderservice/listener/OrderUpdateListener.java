package com.orderservice.listener;

import com.orderservice.model.dto.OrderMessageDTO;
import com.orderservice.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import static com.orderservice.util.Constants.*;

@Service
@AllArgsConstructor
@Log4j2
public class OrderUpdateListener {

    private final OrderService orderService;

    @KafkaListener(topics = UPDATE_ORDER_TOPIC, groupId = CONSUMER_GROUP_ID, containerFactory = KAFKA_LISTENER_FACTORY)
    public void consumeUpdateOrder(@Payload OrderMessageDTO orderMessage) {
        log.info("Consumed Order Update Message: " + orderMessage);
        orderService.updateOrder(orderMessage);
    }
}

