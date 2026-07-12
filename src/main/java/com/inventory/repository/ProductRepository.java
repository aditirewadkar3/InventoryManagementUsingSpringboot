package com.inventory.repository;

import com.inventory.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository
        extends JpaRepository<Product,Long> {

    boolean existsBySku(String sku);

    List<Product> findByNameContainingIgnoreCase(String keyword);
}