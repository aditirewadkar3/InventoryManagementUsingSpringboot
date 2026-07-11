package com.inventory.service.impl;

import com.inventory.constant.AppConstants;
import com.inventory.dto.request.WarehouseRequest;
import com.inventory.dto.response.WarehouseResponse;
import com.inventory.entity.Warehouse;
import com.inventory.exception.ResourceAlreadyExistsException;
import com.inventory.exception.ResourceNotFoundException;
import com.inventory.mapper.WarehouseMapper;
import com.inventory.repository.WarehouseRepository;
import com.inventory.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WarehouseServiceImpl implements WarehouseService {

    private final WarehouseRepository repository;
    private final WarehouseMapper mapper;

    @Override
    public WarehouseResponse create(WarehouseRequest request) {

        if(repository.existsByName(request.getName())){
            throw new ResourceAlreadyExistsException(
                    AppConstants.WAREHOUSE_ALREADY_EXISTS);
        }

        Warehouse warehouse = mapper.toEntity(request);

        return mapper.toResponse(repository.save(warehouse));
    }

    @Override
    public WarehouseResponse getById(Long id) {

        Warehouse warehouse = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                AppConstants.WAREHOUSE_NOT_FOUND));

        return mapper.toResponse(warehouse);
    }

    @Override
    public List<WarehouseResponse> getAll() {

        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public WarehouseResponse update(Long id,
                                    WarehouseRequest request) {

        Warehouse warehouse = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                AppConstants.WAREHOUSE_NOT_FOUND));

        warehouse.setName(request.getName());
        warehouse.setLocation(request.getLocation());
        warehouse.setManagerName(request.getManagerName());
        warehouse.setContactNumber(request.getContactNumber());
        warehouse.setEmail(request.getEmail());

        return mapper.toResponse(repository.save(warehouse));
    }

    @Override
    public void delete(Long id) {

        Warehouse warehouse = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                AppConstants.WAREHOUSE_NOT_FOUND));

        repository.delete(warehouse);
    }
}