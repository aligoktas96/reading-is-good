package com.productservice.service.impl;

import com.productservice.common.exception.BusinessException;
import com.productservice.common.exception.ExceptionCode;
import com.productservice.dao.ProductRepository;
import com.productservice.model.domain.Product;
import com.productservice.model.dto.ProductRequestDTO;
import com.productservice.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product create(ProductRequestDTO productRequestDTO) {
        Product product = Product.builder()
                .name(productRequestDTO.getName())
                .author(productRequestDTO.getAuthor())
                .price(productRequestDTO.getPrice())
                .stock(productRequestDTO.getStock())
                .build();
        return productRepository.save(product);
    }

    @Override
    public Product getProduct(Long productID) {
        Optional<Product> optionalProduct = productRepository.findById(productID);
        Product product;
        if (optionalProduct.isPresent()) {
            product = optionalProduct.get();
        } else {
            throw new BusinessException(ExceptionCode.PRODUCT_NOT_FOUND);
        }
        return product;
    }

    @Transactional
    @Override
    public void decreaseAmount(Long productId, Long amount) {
        productRepository.decreaseStock(productId, amount);
    }
}
