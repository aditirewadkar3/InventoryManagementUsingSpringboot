package com.inventory.repository;

import com.inventory.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository
        extends JpaRepository<Product,Long> {

    boolean existsBySku(String sku);

}