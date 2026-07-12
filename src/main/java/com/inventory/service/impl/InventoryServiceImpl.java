package com.inventory.service.impl;

import com.inventory.constant.AppConstants;
import com.inventory.dto.request.*;
import com.inventory.dto.response.InventoryResponse;
import com.inventory.entity.Inventory;
import com.inventory.entity.Product;
import com.inventory.entity.Warehouse;
import com.inventory.exception.InsufficientStockException;
import com.inventory.exception.ResourceAlreadyExistsException;
import com.inventory.exception.ResourceNotFoundException;
import com.inventory.mapper.InventoryMapper;
import com.inventory.repository.InventoryRepository;
import com.inventory.repository.ProductRepository;
import com.inventory.repository.WarehouseRepository;
import com.inventory.service.InventoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    private final ProductRepository productRepository;

    private final WarehouseRepository warehouseRepository;

    private final InventoryMapper mapper;

    @Override
    public InventoryResponse createInventory(InventoryCreateRequest request) {

        inventoryRepository.findByProductIdAndWarehouseId(
                        request.getProductId(),
                        request.getWarehouseId())
                .ifPresent(i -> {
                    throw new ResourceAlreadyExistsException(
                            "Inventory already exists");
                });

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(AppConstants.PRODUCT_NOT_FOUND));

        Warehouse warehouse = warehouseRepository.findById(request.getWarehouseId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(AppConstants.WAREHOUSE_NOT_FOUND));

        Inventory inventory = Inventory.builder()
                .product(product)
                .warehouse(warehouse)
                .quantity(request.getQuantity())
                .build();

        return mapper.toResponse(inventoryRepository.save(inventory));
    }

    @Override
    public InventoryResponse stockIn(StockInRequest request) {

        Inventory inventory = inventoryRepository
                .findByProductIdAndWarehouseId(
                        request.getProductId(),
                        request.getWarehouseId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(AppConstants.INVENTORY_NOT_FOUND));

        inventory.setQuantity(
                inventory.getQuantity() + request.getQuantity());

        return mapper.toResponse(inventoryRepository.save(inventory));
    }

    @Override
    public InventoryResponse stockOut(StockOutRequest request) {

        Inventory inventory = inventoryRepository
                .findByProductIdAndWarehouseId(
                        request.getProductId(),
                        request.getWarehouseId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(AppConstants.INVENTORY_NOT_FOUND));

        if (inventory.getQuantity() < request.getQuantity()) {
            throw new InsufficientStockException(
                    AppConstants.INSUFFICIENT_STOCK);
        }

        inventory.setQuantity(
                inventory.getQuantity() - request.getQuantity());

        return mapper.toResponse(inventoryRepository.save(inventory));
    }

    @Override
    public void transferStock(TransferStockRequest request) {

        Inventory source = inventoryRepository
                .findByProductIdAndWarehouseId(
                        request.getProductId(),
                        request.getSourceWarehouseId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(AppConstants.INVENTORY_NOT_FOUND));

        Inventory destination = inventoryRepository
                .findByProductIdAndWarehouseId(
                        request.getProductId(),
                        request.getDestinationWarehouseId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(AppConstants.INVENTORY_NOT_FOUND));

        if (source.getQuantity() < request.getQuantity()) {
            throw new InsufficientStockException(
                    AppConstants.INSUFFICIENT_STOCK);
        }

        source.setQuantity(
                source.getQuantity() - request.getQuantity());

        destination.setQuantity(
                destination.getQuantity() + request.getQuantity());

        inventoryRepository.save(source);
        inventoryRepository.save(destination);
    }

    @Override
    public InventoryResponse getInventory(Long id) {

        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(AppConstants.INVENTORY_NOT_FOUND));

        return mapper.toResponse(inventory);
    }

    @Override
    public List<InventoryResponse> getAllInventory() {

        return inventoryRepository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public List<InventoryResponse> lowStock() {

        return inventoryRepository
                .findByQuantityLessThanEqual(10)
                .stream()
                .map(mapper::toResponse)
                .toList();

    }
}