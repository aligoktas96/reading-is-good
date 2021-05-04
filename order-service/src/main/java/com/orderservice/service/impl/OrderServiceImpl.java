package com.orderservice.service.impl;

import com.orderservice.common.exception.BusinessException;
import com.orderservice.common.exception.ExceptionCode;
import com.orderservice.dao.OrderRepository;
import com.orderservice.feignclient.ProductResource;
import com.orderservice.model.domain.Order;
import com.orderservice.model.dto.OrderMessageDTO;
import com.orderservice.model.dto.OrderRequestDTO;
import com.orderservice.model.dto.OrderStatus;
import com.orderservice.model.dto.ProductDTO;
import com.orderservice.service.OrderService;
import com.orderservice.util.Constants;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final KafkaTemplate<String, OrderMessageDTO> kafkaTemplate;

    private final OrderRepository orderRepository;

    private final ProductResource productResource;

    @Override
    public Order create(OrderRequestDTO orderDTO) {
        ProductDTO productDTO = getProduct(orderDTO);

        validateProductAmount(orderDTO, productDTO);

        Order recordedOrder = orderRepository.save(toEntity(orderDTO, productDTO));

        sendKafkaMessage(recordedOrder);

        return recordedOrder;
    }

    @Override
    @Transactional
    public void updateOrder(OrderMessageDTO orderMessageDTO) {
        Optional<Order> orderOptional = orderRepository.findById(orderMessageDTO.getOrderID());

        if (orderOptional.isPresent()) {
            updateOrderStatus(orderMessageDTO, orderOptional.get());
        } else {
            throw new BusinessException(ExceptionCode.ORDER_NOT_FOUND);
        }
    }

    private void updateOrderStatus(OrderMessageDTO orderMessageDTO, Order order) {
        order.setStatus(orderMessageDTO.getStatus());
        orderRepository.save(order);
    }

    @Override
    public List<Order> getOrderByCustomerID(Long customerID) {
        return orderRepository.findAllByCustomerID(customerID);
    }

    @Override
    public Order getOrder(Long orderID) {
        Optional<Order> orderOptional = orderRepository.findById(orderID);

        if (orderOptional.isEmpty()) {
            throw new BusinessException(ExceptionCode.ORDER_NOT_FOUND);
        }

        return orderOptional.get();
    }

    private void sendKafkaMessage(Order recordedOrder) {
        kafkaTemplate.send(Constants.NEW_ORDER_TOPIC, toDTO(recordedOrder));
    }

    private void validateProductAmount(OrderRequestDTO orderDTO, ProductDTO productDTO) {
        boolean isOrderAmountGraterThanStock = productDTO.getStock() < orderDTO.getAmount();

        if (isOrderAmountGraterThanStock) {
            throw new BusinessException(ExceptionCode.PRODUCT_AMOUNT_INSUFFICIENT);
        }
    }

    private ProductDTO getProduct(OrderRequestDTO orderDTO) {
        return productResource.getProduct(orderDTO.getProductID());
    }

    private OrderMessageDTO toDTO(Order recordedOrder) {
        return OrderMessageDTO.builder()
                .orderID(recordedOrder.getId())
                .customerID(recordedOrder.getCustomerID())
                .productID(recordedOrder.getProductID())
                .amount(recordedOrder.getAmount())
                .status(recordedOrder.getStatus())
                .build();
    }

    private Order toEntity(OrderRequestDTO orderDTO, ProductDTO productDTO) {
        return Order.builder()
                .amount(orderDTO.getAmount())
                .customerID(orderDTO.getCustomerID())
                .productID(orderDTO.getProductID())
                .totalPrice(orderDTO.getAmount() * productDTO.getPrice())
                .status(OrderStatus.PENDING)
                .build();
    }

}