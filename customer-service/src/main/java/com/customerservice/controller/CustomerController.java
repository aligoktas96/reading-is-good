package com.customerservice.controller;

import com.customerservice.common.lifecycle.BaseController;
import com.customerservice.common.response.Response;
import com.customerservice.model.dto.CustomerRequestDTO;
import com.customerservice.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
@AllArgsConstructor
public class CustomerController extends BaseController {

    private final CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<Response> newCustomer(@RequestBody CustomerRequestDTO customerRequestDTO){
        return ok(customerService.newCustomer(customerRequestDTO));
    }

}
