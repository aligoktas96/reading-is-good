package com.productservice.model.dto;


import lombok.Data;

import java.io.Serializable;

@Data
public class OrderMessageDTO  implements Serializable {
    private Long orderID;
    private Long productID;
    private Long amount;
    private Long customerID;
    private OrderStatus status;
}
