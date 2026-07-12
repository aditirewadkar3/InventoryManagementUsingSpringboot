package com.inventory.service;

import com.inventory.dto.request.*;
import com.inventory.dto.response.InventoryResponse;

import java.util.List;

public interface InventoryService {

    InventoryResponse createInventory(
            InventoryCreateRequest request);

    InventoryResponse stockIn(
            StockInRequest request);

    InventoryResponse stockOut(
            StockOutRequest request);

    void transferStock(
            TransferStockRequest request);

    InventoryResponse getInventory(Long id);

    List<InventoryResponse> getAllInventory();

    List<InventoryResponse> lowStock();
}