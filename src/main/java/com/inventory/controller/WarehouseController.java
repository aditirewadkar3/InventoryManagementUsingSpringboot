package com.inventory.controller;

import com.inventory.constant.ApiResponse;
import com.inventory.constant.AppConstants;
import com.inventory.dto.request.WarehouseRequest;
import com.inventory.dto.response.WarehouseResponse;
import com.inventory.service.WarehouseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/warehouses")
@RequiredArgsConstructor
public class WarehouseController {

    private final WarehouseService service;

    @PostMapping
    public ResponseEntity<ApiResponse<WarehouseResponse>> create(
            @Valid @RequestBody WarehouseRequest request){

        WarehouseResponse response = service.create(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<WarehouseResponse>builder()
                        .success(true)
                        .message(AppConstants.WAREHOUSE_CREATED)
                        .data(response)
                        .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<WarehouseResponse>> getById(
            @PathVariable Long id){

        return ResponseEntity.ok(
                ApiResponse.<WarehouseResponse>builder()
                        .success(true)
                        .message("Warehouse fetched successfully")
                        .data(service.getById(id))
                        .build());
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<WarehouseResponse>>> getAll(){

        return ResponseEntity.ok(
                ApiResponse.<List<WarehouseResponse>>builder()
                        .success(true)
                        .message("Warehouses fetched successfully")
                        .data(service.getAll())
                        .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<WarehouseResponse>> update(
            @PathVariable Long id,
            @Valid @RequestBody WarehouseRequest request){

        return ResponseEntity.ok(
                ApiResponse.<WarehouseResponse>builder()
                        .success(true)
                        .message(AppConstants.WAREHOUSE_UPDATED)
                        .data(service.update(id, request))
                        .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(
            @PathVariable Long id){

        service.delete(id);

        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .success(true)
                        .message(AppConstants.WAREHOUSE_DELETED)
                        .data(null)
                        .build());
    }
}