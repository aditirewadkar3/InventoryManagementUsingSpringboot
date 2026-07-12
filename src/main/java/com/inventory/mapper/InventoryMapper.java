package com.inventory.mapper;

import com.inventory.dto.response.InventoryResponse;
import com.inventory.entity.Inventory;
import org.springframework.stereotype.Component;

@Component
public class InventoryMapper {

    public InventoryResponse toResponse(Inventory inventory){

        return InventoryResponse.builder()
                .id(inventory.getId())
                .productName(inventory.getProduct().getName())
                .warehouseName(inventory.getWarehouse().getName())
                .quantity(inventory.getQuantity())
                .build();

    }

}