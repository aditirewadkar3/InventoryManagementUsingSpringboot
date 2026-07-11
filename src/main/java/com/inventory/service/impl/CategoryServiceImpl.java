package com.inventory.service.impl;

import com.inventory.dto.CategoryRequest;
import com.inventory.dto.CategoryResponse;
import com.inventory.entity.Category;
import com.inventory.exception.ResourceAlreadyExistsException;
import com.inventory.exception.ResourceNotFoundException;
import com.inventory.repository.CategoryRepository;
import com.inventory.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;

    public CategoryServiceImpl(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public CategoryResponse createCategory(CategoryRequest request) {

        if (repository.findByName(request.getName()).isPresent()) {
            throw new ResourceAlreadyExistsException("Category already exists");
        }

        Category category = new Category();

        category.setName(request.getName());
        category.setDescription(request.getDescription());

        Category saved = repository.save(category);

        return new CategoryResponse(
                saved.getId(),
                saved.getName(),
                saved.getDescription()
        );
    }

    @Override
    public List<CategoryResponse> getAllCategories() {

        return repository.findAll()
                .stream()
                .map(category -> new CategoryResponse(
                        category.getId(),
                        category.getName(),
                        category.getDescription()))
                .toList();
    }

    @Override
    public CategoryResponse getCategoryById(Long id) {

        Category category = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category not found"));

        return new CategoryResponse(
                category.getId(),
                category.getName(),
                category.getDescription()
        );
    }

    @Override
    public CategoryResponse updateCategory(Long id, CategoryRequest request) {

        Category category = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category not found"));

        category.setName(request.getName());
        category.setDescription(request.getDescription());

        Category updated = repository.save(category);

        return new CategoryResponse(
                updated.getId(),
                updated.getName(),
                updated.getDescription()
        );
    }

    @Override
    public void deleteCategory(Long id) {

        Category category = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category not found"));

        repository.delete(category);

    }

}