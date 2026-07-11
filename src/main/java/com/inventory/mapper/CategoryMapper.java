package com.inventory.mapper;

import com.inventory.dto.request.CategoryRequest;
import com.inventory.dto.response.CategoryResponse;
import com.inventory.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public Category toEntity(CategoryRequest request) {

        return Category.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public CategoryResponse toResponse(Category category) {

        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }
}