package com.orderservice.model.domain;

import com.orderservice.model.dto.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "orders")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productID;
    private Long amount;
    private Long customerID;
    private Double totalPrice;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
}