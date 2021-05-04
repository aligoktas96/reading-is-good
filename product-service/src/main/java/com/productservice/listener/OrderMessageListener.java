package com.productservice.listener;


import com.productservice.model.dto.OrderMessageDTO;
import com.productservice.model.dto.OrderStatus;
import com.productservice.service.ProductService;
import com.productservice.util.Constants;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import static com.productservice.util.Constants.*;

@Service
@Log4j2
@AllArgsConstructor
public class OrderMessageListener {

    private final ProductService productService;
    private final KafkaTemplate<String, OrderMessageDTO> kafkaTemplate;

    @KafkaListener(topics = NEW_ORDER_TOPIC, groupId = CONSUMER_GROUP_ID, containerFactory = KAFKA_LISTENER_FACTORY)
    public void consumeNewOrder(@Payload OrderMessageDTO orderMessage) {

        log.info("Consumed newOrder Message: " + orderMessage);

        decreaseStockAndUpdateOrderStatus(orderMessage);

        kafkaTemplate.send(Constants.UPDATE_ORDER_TOPIC, orderMessage);
    }

    private void decreaseStockAndUpdateOrderStatus(OrderMessageDTO orderMessage) {
        try {
            productService.decreaseAmount(orderMessage.getProductID(), orderMessage.getAmount());
            orderMessage.setStatus(OrderStatus.SUCCESS);
        } catch (Exception e) {
            log.info("Failed decrease amount: message: " + orderMessage);
            orderMessage.setStatus(OrderStatus.FAIL);
        }
    }
}