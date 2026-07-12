package com.inventory.controller;

import com.inventory.constant.ApiResponse;
import com.inventory.constant.AppConstants;
import com.inventory.dto.request.InventoryCreateRequest;
import com.inventory.dto.request.StockInRequest;
import com.inventory.dto.request.StockOutRequest;
import com.inventory.dto.request.TransferStockRequest;
import com.inventory.dto.response.InventoryResponse;
import com.inventory.service.InventoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<InventoryResponse>> createInventory(
            @Valid @RequestBody InventoryCreateRequest request) {

        InventoryResponse response = inventoryService.createInventory(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<InventoryResponse>builder()
                        .success(true)
                        .message(AppConstants.INVENTORY_CREATED)
                        .data(response)
                        .build());
    }

    @PostMapping("/stock-in")
    public ResponseEntity<ApiResponse<InventoryResponse>> stockIn(
            @Valid @RequestBody StockInRequest request) {

        InventoryResponse response = inventoryService.stockIn(request);

        return ResponseEntity.ok(
                ApiResponse.<InventoryResponse>builder()
                        .success(true)
                        .message(AppConstants.STOCK_IN_SUCCESS)
                        .data(response)
                        .build());
    }

    @PostMapping("/stock-out")
    public ResponseEntity<ApiResponse<InventoryResponse>> stockOut(
            @Valid @RequestBody StockOutRequest request) {

        InventoryResponse response = inventoryService.stockOut(request);

        return ResponseEntity.ok(
                ApiResponse.<InventoryResponse>builder()
                        .success(true)
                        .message(AppConstants.STOCK_OUT_SUCCESS)
                        .data(response)
                        .build());
    }

    @PostMapping("/transfer")
    public ResponseEntity<ApiResponse<Void>> transferStock(
            @Valid @RequestBody TransferStockRequest request) {

        inventoryService.transferStock(request);

        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .success(true)
                        .message(AppConstants.TRANSFER_SUCCESS)
                        .data(null)
                        .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<InventoryResponse>> getInventory(
            @PathVariable Long id) {

        InventoryResponse response = inventoryService.getInventory(id);

        return ResponseEntity.ok(
                ApiResponse.<InventoryResponse>builder()
                        .success(true)
                        .message("Inventory fetched successfully")
                        .data(response)
                        .build());
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<InventoryResponse>>> getAllInventory() {

        List<InventoryResponse> response = inventoryService.getAllInventory();

        return ResponseEntity.ok(
                ApiResponse.<List<InventoryResponse>>builder()
                        .success(true)
                        .message("Inventory fetched successfully")
                        .data(response)
                        .build());
    }
    @GetMapping("/low-stock")
    public ResponseEntity<ApiResponse<List<InventoryResponse>>> lowStock(){

        return ResponseEntity.ok(
                ApiResponse.<List<InventoryResponse>>builder()
                        .success(true)
                        .message("Low stock fetched")
                        .data(inventoryService.lowStock())
                        .build());

    }

}