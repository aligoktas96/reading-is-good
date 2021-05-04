package com.productservice.dao;

import com.productservice.model.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Modifying
    @Query("update Product p set p.stock = (p.stock) - (:amount) where p.id = :productID")
    void decreaseStock(@Param(value = "productID") Long productID, @Param(value = "amount") Long amount);
}
