package com.orderservice.service;

import com.orderservice.model.domain.Order;
import com.orderservice.model.dto.OrderMessageDTO;
import com.orderservice.model.dto.OrderRequestDTO;

import java.util.List;

public interface OrderService {
    Order create(OrderRequestDTO orderDTO);

    List<Order> getOrderByCustomerID(Long customerID);

    Order getOrder(Long orderID);

    void updateOrder(OrderMessageDTO orderMessageDTO);
}