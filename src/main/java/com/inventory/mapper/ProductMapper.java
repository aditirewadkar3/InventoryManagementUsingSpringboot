package com.inventory.mapper;

import com.inventory.dto.response.ProductResponse;
import com.inventory.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductResponse toResponse(Product product){

        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .sku(product.getSku())
                .sellingPrice(product.getSellingPrice())
                .costPrice(product.getCostPrice())
                .description(product.getDescription())
                .barcode(product.getBarcode())
                .qrCode(product.getQrCode())
                .categoryName(product.getCategory().getName())
                .build();

    }

}