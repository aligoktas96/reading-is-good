package com.orderservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDTO {

    private Long orderID;
    private Long productID;
    private Long customerID;
    private Long amount;
    private OrderStatus status;
}