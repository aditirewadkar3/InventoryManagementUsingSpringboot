package com.inventory.service.impl;

import com.inventory.constant.AppConstants;
import com.inventory.dto.request.ProductRequest;
import com.inventory.dto.response.ProductResponse;
import com.inventory.entity.Category;
import com.inventory.entity.Product;
import com.inventory.exception.ResourceNotFoundException;
import com.inventory.mapper.ProductMapper;
import com.inventory.repository.CategoryRepository;
import com.inventory.repository.ProductRepository;
import com.inventory.service.ProductService;
import com.inventory.util.BarcodeGenerator;
import com.inventory.util.QrCodeGenerator;
import com.inventory.util.SkuGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    private final ProductMapper productMapper;

    private final SkuGenerator skuGenerator;

    private final BarcodeGenerator barcodeGenerator;

    private final QrCodeGenerator qrCodeGenerator;

    @Override
    public ProductResponse create(ProductRequest request) {

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(AppConstants.CATEGORY_NOT_FOUND));

        String sku = skuGenerator.generateSku(
                request.getName(),
                productRepository.count()
        );

        String barcode = barcodeGenerator.generateBarcode(sku);

        String qr = qrCodeGenerator.generateQrCode(
                sku,
                "Name: " + request.getName()
                        + "\nSKU: " + sku
                        + "\nSelling Price: " + request.getSellingPrice()
        );

        Product product = Product.builder()
                .name(request.getName())
                .sku(sku)
                .sellingPrice(request.getSellingPrice())
                .costPrice(request.getCostPrice())
                .description(request.getDescription())
                .barcode(barcode)
                .qrCode(qr)
                .category(category)
                .build();

        Product savedProduct = productRepository.save(product);

        return productMapper.toResponse(savedProduct);

    }

    @Override
    public ProductResponse getById(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(AppConstants.PRODUCT_NOT_FOUND));

        return productMapper.toResponse(product);

    }

    @Override
    public List<ProductResponse> getAll() {

        return productRepository.findAll()
                .stream()
                .map(productMapper::toResponse)
                .toList();

    }

    @Override
    public ProductResponse update(Long id, ProductRequest request) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(AppConstants.PRODUCT_NOT_FOUND));

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(AppConstants.CATEGORY_NOT_FOUND));

        product.setName(request.getName());
        product.setSellingPrice(request.getSellingPrice());
        product.setCostPrice(request.getCostPrice());
        product.setDescription(request.getDescription());
        product.setCategory(category);

        Product updated = productRepository.save(product);

        return productMapper.toResponse(updated);

    }

    @Override
    public void delete(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(AppConstants.PRODUCT_NOT_FOUND));

        productRepository.delete(product);

    }

}