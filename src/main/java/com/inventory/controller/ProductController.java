package com.inventory.controller;

import com.inventory.dto.request.ProductRequest;
import com.inventory.dto.response.ProductResponse;
import com.inventory.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service){
        this.service=service;
    }

    @PostMapping
    public ProductResponse create(
            @Valid @RequestBody ProductRequest request){

        return service.create(request);
    }

    @GetMapping
    public List<ProductResponse> getAll(){

        return service.getAll();
    }

    @GetMapping("/{id}")
    public ProductResponse getById(
            @PathVariable Long id){

        return service.getById(id);
    }

    @PutMapping("/{id}")
    public ProductResponse update(
            @PathVariable Long id,
            @Valid @RequestBody ProductRequest request){

        return service.update(id,request);
    }

    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Long id){

        service.delete(id);
    }
}