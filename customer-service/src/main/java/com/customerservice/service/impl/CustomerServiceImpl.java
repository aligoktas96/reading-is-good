package com.customerservice.service.impl;

import com.customerservice.dao.CustomerRepository;
import com.customerservice.model.domain.Customer;
import com.customerservice.model.dto.CustomerRequestDTO;
import com.customerservice.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public Customer newCustomer(CustomerRequestDTO customerRequestDTO) {
        return customerRepository.save(toEntity(customerRequestDTO));
    }

    private Customer toEntity(CustomerRequestDTO customerRequestDTO) {
        return Customer.builder()
                .username(customerRequestDTO.getUsername())
                .password(customerRequestDTO.getPassword())
                .build();
    }
}
