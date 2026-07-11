package com.inventory.service;

import com.inventory.dto.request.CategoryRequest;
import com.inventory.dto.response.CategoryResponse;

import java.util.List;

public interface CategoryService {

    CategoryResponse create(CategoryRequest request);

    CategoryResponse getById(Long id);

    List<CategoryResponse> getAll();

    CategoryResponse update(Long id, CategoryRequest request);

    void delete(Long id);

}