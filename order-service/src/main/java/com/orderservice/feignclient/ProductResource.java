package com.orderservice.feignclient;

import com.orderservice.model.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("product-service")
public interface ProductResource {

    @GetMapping("/product/{productID}")
    ProductDTO getProduct(@PathVariable Long productID);

}
