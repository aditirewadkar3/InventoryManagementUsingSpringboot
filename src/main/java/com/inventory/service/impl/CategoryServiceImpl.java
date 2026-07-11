package com.inventory.service.impl;

import com.inventory.constant.AppConstants;
import com.inventory.dto.request.CategoryRequest;
import com.inventory.dto.response.CategoryResponse;
import com.inventory.entity.Category;
import com.inventory.exception.ResourceAlreadyExistsException;
import com.inventory.exception.ResourceNotFoundException;
import com.inventory.mapper.CategoryMapper;
import com.inventory.repository.CategoryRepository;
import com.inventory.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl
        implements CategoryService {

    private final CategoryRepository repository;

    private final CategoryMapper mapper;

    @Override
    public CategoryResponse create(CategoryRequest request) {

        if(repository.existsByName(request.getName())){

            throw new ResourceAlreadyExistsException(
                    AppConstants.CATEGORY_ALREADY_EXISTS);
        }

        Category category = mapper.toEntity(request);

        Category saved = repository.save(category);

        return mapper.toResponse(saved);

    }

    @Override
    public CategoryResponse getById(Long id) {

        Category category = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                AppConstants.CATEGORY_NOT_FOUND));

        return mapper.toResponse(category);

    }

    @Override
    public List<CategoryResponse> getAll() {

        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();

    }

    @Override
    public CategoryResponse update(Long id,
                                   CategoryRequest request) {

        Category category = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                AppConstants.CATEGORY_NOT_FOUND));

        category.setName(request.getName());

        category.setDescription(request.getDescription());

        return mapper.toResponse(repository.save(category));

    }

    @Override
    public void delete(Long id) {

        Category category = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                AppConstants.CATEGORY_NOT_FOUND));

        repository.delete(category);

    }

}