package com.inventory.service.impl;

import com.inventory.dto.response.DashboardResponse;
import com.inventory.repository.CategoryRepository;
import com.inventory.repository.InventoryRepository;
import com.inventory.repository.ProductRepository;
import com.inventory.repository.WarehouseRepository;
import com.inventory.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final CategoryRepository categoryRepository;

    private final ProductRepository productRepository;

    private final WarehouseRepository warehouseRepository;

    private final InventoryRepository inventoryRepository;

    @Override
    public DashboardResponse getDashboard() {

        return DashboardResponse.builder()
                .totalCategories(categoryRepository.count())
                .totalProducts(productRepository.count())
                .totalWarehouses(warehouseRepository.count())
                .totalInventoryRecords(inventoryRepository.count())
                .lowStockProducts(inventoryRepository.countByQuantityLessThanEqual(10))
                .build();

    }
}