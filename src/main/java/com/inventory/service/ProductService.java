package com.inventory.service;

import com.inventory.dto.request.ProductRequest;
import com.inventory.dto.response.ProductResponse;

import java.util.List;

public interface ProductService {

    ProductResponse create(ProductRequest request);

    ProductResponse getById(Long id);

    List<ProductResponse> getAll();

    ProductResponse update(Long id, ProductRequest request);

    void delete(Long id);

}