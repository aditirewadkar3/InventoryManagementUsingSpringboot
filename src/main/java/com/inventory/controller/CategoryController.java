package com.inventory.controller;

import com.inventory.constant.ApiResponse;
import com.inventory.constant.AppConstants;
import com.inventory.dto.request.CategoryRequest;
import com.inventory.dto.response.CategoryResponse;
import com.inventory.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/api/categories")

@RequiredArgsConstructor

public class CategoryController {

    private final CategoryService service;

    @PostMapping
    public ResponseEntity<ApiResponse<CategoryResponse>>
    create(@Valid @RequestBody CategoryRequest request){

        CategoryResponse response = service.create(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<CategoryResponse>builder()
                        .success(true)
                        .message(AppConstants.CATEGORY_CREATED)
                        .data(response)
                        .build());

    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponse>>
    getById(@PathVariable Long id){

        CategoryResponse response = service.getById(id);

        return ResponseEntity.ok(
                ApiResponse.<CategoryResponse>builder()
                        .success(true)
                        .message("Category Found")
                        .data(response)
                        .build());

    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CategoryResponse>>>
    getAll(){

        List<CategoryResponse> response =
                service.getAll();

        return ResponseEntity.ok(
                ApiResponse.<List<CategoryResponse>>builder()
                        .success(true)
                        .message("All Categories")
                        .data(response)
                        .build());

    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponse>>
    update(@PathVariable Long id,
           @Valid @RequestBody CategoryRequest request){

        CategoryResponse response =
                service.update(id,request);

        return ResponseEntity.ok(
                ApiResponse.<CategoryResponse>builder()
                        .success(true)
                        .message(AppConstants.CATEGORY_UPDATED)
                        .data(response)
                        .build());

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>>
    delete(@PathVariable Long id){

        service.delete(id);

        return ResponseEntity.ok(
                ApiResponse.builder()
                        .success(true)
                        .message(AppConstants.CATEGORY_DELETED)
                        .data(null)
                        .build());

    }

}