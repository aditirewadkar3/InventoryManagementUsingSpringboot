package com.inventory.repository;

import com.inventory.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository
        extends JpaRepository<Inventory,Long> {

    Optional<Inventory> findByProductIdAndWarehouseId(
            Long productId,
            Long warehouseId
    );

    long countByQuantityLessThanEqual(Integer quantity);
    List<Inventory> findByQuantityLessThanEqual(Integer quantity);

}