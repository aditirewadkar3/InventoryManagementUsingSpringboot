package com.inventory.service.impl;

import com.inventory.dto.request.ProductRequest;
import com.inventory.dto.response.ProductResponse;
import com.inventory.entity.*;
import com.inventory.exception.*;
import com.inventory.repository.*;
import com.inventory.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl
        implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository,
                              CategoryRepository categoryRepository){

        this.productRepository=productRepository;
        this.categoryRepository=categoryRepository;
    }

    @Override
    public ProductResponse create(ProductRequest request){

        Category category=categoryRepository.findById(
                        request.getCategoryId())
                .orElseThrow(()->
                        new ResourceNotFoundException(
                                "Category Not Found"));

        Product product=new Product();

        product.setName(request.getName());

        product.setSku(UUID.randomUUID()
                .toString()
                .substring(0,8)
                .toUpperCase());

        product.setSellingPrice(request.getSellingPrice());

        product.setCostPrice(request.getCostPrice());

        product.setDescription(request.getDescription());

        product.setCategory(category);

        Product saved=productRepository.save(product);

        return new ProductResponse(
                saved.getId(),
                saved.getName(),
                saved.getSku(),
                saved.getSellingPrice(),
                saved.getCostPrice(),
                saved.getDescription(),
                saved.getCategory().getName()
        );
    }

    @Override
    public List<ProductResponse> getAll(){

        return productRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public ProductResponse getById(Long id){

        Product product=productRepository.findById(id)
                .orElseThrow(()->
                        new ResourceNotFoundException(
                                "Product Not Found"));

        return mapToResponse(product);
    }

    @Override
    public ProductResponse update(Long id,
                                  ProductRequest request){

        Product product=productRepository.findById(id)
                .orElseThrow(()->
                        new ResourceNotFoundException(
                                "Product Not Found"));

        Category category=categoryRepository.findById(
                        request.getCategoryId())
                .orElseThrow(()->
                        new ResourceNotFoundException(
                                "Category Not Found"));

        product.setName(request.getName());
        product.setSellingPrice(request.getSellingPrice());
        product.setCostPrice(request.getCostPrice());
        product.setDescription(request.getDescription());
        product.setCategory(category);

        return mapToResponse(
                productRepository.save(product));
    }

    @Override
    public void delete(Long id){

        Product product=productRepository.findById(id)
                .orElseThrow(()->
                        new ResourceNotFoundException(
                                "Product Not Found"));

        productRepository.delete(product);
    }

    private ProductResponse mapToResponse(Product p){

        return new ProductResponse(
                p.getId(),
                p.getName(),
                p.getSku(),
                p.getSellingPrice(),
                p.getCostPrice(),
                p.getDescription(),
                p.getCategory().getName()
        );
    }
}