package com.inventory.service;

import com.inventory.dto.request.ProductRequest;
import com.inventory.dto.response.ProductResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface ProductService {

    ProductResponse create(ProductRequest request);

    ProductResponse getById(Long id);

    List<ProductResponse> getAll();

    ProductResponse update(Long id, ProductRequest request);

    void delete(Long id);

    ByteArrayInputStream exportProducts();
    void importProducts(MultipartFile file);
    List<ProductResponse> search(String keyword);

}