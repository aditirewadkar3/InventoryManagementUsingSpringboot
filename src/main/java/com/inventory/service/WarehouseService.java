package com.inventory.service;

import com.inventory.dto.request.WarehouseRequest;
import com.inventory.dto.response.WarehouseResponse;

import java.util.List;

public interface WarehouseService {

    WarehouseResponse create(WarehouseRequest request);

    WarehouseResponse getById(Long id);

    List<WarehouseResponse> getAll();

    WarehouseResponse update(Long id,
                             WarehouseRequest request);

    void delete(Long id);

}