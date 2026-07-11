package com.inventory.repository;

import com.inventory.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseRepository
        extends JpaRepository<Warehouse,Long> {

    boolean existsByName(String name);

}