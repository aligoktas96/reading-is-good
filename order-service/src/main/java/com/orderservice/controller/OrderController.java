package com.orderservice.controller;

import com.orderservice.common.lifecycle.BaseController;
import com.orderservice.common.response.Response;
import com.orderservice.model.dto.OrderRequestDTO;
import com.orderservice.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@AllArgsConstructor
public class OrderController extends BaseController {

    private final OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<Response> create(@RequestBody OrderRequestDTO orderDTO) {
        return ok(orderService.create(orderDTO));
    }

    @GetMapping("/customer/{customerID}")
    public ResponseEntity<Response> getOrderByCustomer(@PathVariable("customerID") Long customerID){
        return ok(orderService.getOrderByCustomerID(customerID));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getOrder(@PathVariable("id") Long orderID){
        return ok(orderService.getOrder(orderID));
    }
}
