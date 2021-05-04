package com.productservice.service;

import com.productservice.model.domain.Product;
import com.productservice.model.dto.ProductRequestDTO;


public interface ProductService {
    Product create(ProductRequestDTO productRequestDTO);
    Product getProduct(Long id);
    void decreaseAmount(Long productId, Long amount);
}
