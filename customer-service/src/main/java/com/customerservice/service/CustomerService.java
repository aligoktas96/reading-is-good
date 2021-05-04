package com.customerservice.service;

import com.customerservice.model.domain.Customer;
import com.customerservice.model.dto.CustomerRequestDTO;

public interface CustomerService {
    Customer newCustomer(CustomerRequestDTO customerRequestDTO);
}
