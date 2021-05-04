package com.orderservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderMessageDTO implements Serializable {

    private Long orderID;
    private Long productID;
    private Long amount;
    private Long customerID;
    private OrderStatus status;
}
