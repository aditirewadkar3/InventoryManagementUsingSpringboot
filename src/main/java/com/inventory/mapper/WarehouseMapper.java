package com.inventory.mapper;

import com.inventory.dto.request.WarehouseRequest;
import com.inventory.dto.response.WarehouseResponse;
import com.inventory.entity.Warehouse;
import org.springframework.stereotype.Component;

@Component
public class WarehouseMapper {

    public Warehouse toEntity(WarehouseRequest request){

        return Warehouse.builder()
                .name(request.getName())
                .location(request.getLocation())
                .managerName(request.getManagerName())
                .contactNumber(request.getContactNumber())
                .email(request.getEmail())
                .build();

    }

    public WarehouseResponse toResponse(Warehouse warehouse){

        return WarehouseResponse.builder()
                .id(warehouse.getId())
                .name(warehouse.getName())
                .location(warehouse.getLocation())
                .managerName(warehouse.getManagerName())
                .contactNumber(warehouse.getContactNumber())
                .email(warehouse.getEmail())
                .build();

    }

}