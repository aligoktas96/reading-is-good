package com.productservice.controller;

import com.productservice.common.lifecycle.BaseController;
import com.productservice.common.response.Response;
import com.productservice.model.domain.Product;
import com.productservice.model.dto.ProductRequestDTO;
import com.productservice.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController extends BaseController {
    private final ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<Response> create(@RequestBody ProductRequestDTO productRequestDTO) {
        return ok(productService.create(productRequestDTO));
    }

    @GetMapping("/{productID}")
    public Product getProduct(@PathVariable("productID") Long productID) {
        return productService.getProduct(productID);
    }
}
